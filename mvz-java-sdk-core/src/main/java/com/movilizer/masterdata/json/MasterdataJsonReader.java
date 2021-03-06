package com.movilizer.masterdata.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.inject.Singleton;
import com.movilizer.masterdata.*;
import com.movilizer.util.json.JsonUtils;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
@Singleton
public class MasterdataJsonReader extends MasterdataReader implements IMasterdataJsonReader {

    @Override
    public List<IParsedMasterdataEvent> parse(Reader reader, IMasterdataXmlSetting settings) throws XMLStreamException, IOException {
        JsonReader jsonReader = new JsonReader(reader);
        try {
            return readMasterdataEvents(jsonReader, settings);
        } finally {
            jsonReader.close();
        }
    }


    @Override
    public IMasterdataReaderResult readArray(JsonArray array, IMasterdataXmlSetting settings) throws IOException, XMLStreamException {
        List<IParsedMasterdataEvent> elements = convertMasterdataEvents(array, settings);
        return toReaderResult(settings, elements);
    }

    public List<IParsedMasterdataEvent> readMasterdataEvents(JsonReader reader, IMasterdataXmlSetting settings) throws IOException {
        List<IParsedMasterdataEvent> events = newArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            events.add(readEvent(reader, settings));
        }
        reader.endArray();
        return events;
    }

    public List<IParsedMasterdataEvent> convertMasterdataEvents(JsonArray array, IMasterdataXmlSetting settings) throws IOException {
        List<IParsedMasterdataEvent> events = newArrayList();

        for (JsonElement jsonElement : array) {
            events.add(toMasterdataEvent(jsonElement.getAsJsonObject(), settings));
        }
        return events;
    }

    private IParsedMasterdataEvent readEvent(JsonReader reader, IMasterdataXmlSetting settings) {
        JsonElement parse = new JsonParser().parse(reader);
        JsonObject jsonObject = parse.getAsJsonObject();
        return toMasterdataEvent(jsonObject, settings);
    }

    private IParsedMasterdataEvent toMasterdataEvent(JsonObject jsonObject, IMasterdataXmlSetting settings) {
        return new ParsedMasterdataEvent(JsonUtils.collectPrimitiveProperties(jsonObject, true), settings.getFieldNames());
    }

}
