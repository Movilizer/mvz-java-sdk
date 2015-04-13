package com.movilizer.masterdata.excel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.movilizer.push.EventType;
import com.movilizer.util.json.JsonUtils;
import com.movilizer.util.logger.ComponentLogger;
import com.movilizer.util.logger.ILogger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

import static com.movilizer.util.collection.CollectionUtils.skip;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
public class CsvToJsonConverter {

    private final ILogger logger = ComponentLogger.getInstance(CsvToJsonConverter.class);

    public JsonArray convert(Reader csvReader, String idFieldName, int offset, int limit) throws IOException {
        return convert(csvReader, offset, limit, new AddEventIdAndType(EventType.CREATE, idFieldName));
    }

    public JsonArray convert(Reader csvReader, int offset, int limit, Operation2<JsonObject, Integer> processRow) throws IOException {

        CSVFormat csvFormat = CSVFormat.newFormat(';').withHeader();
        Iterator<CSVRecord> records = csvFormat.parse(csvReader).iterator();
        skip(records, offset);
        JsonArray res = new JsonArray();

        int i = 0;
        while (records.hasNext()) {
            CSVRecord record = records.next();
            JsonObject jsonObject = convert(record);
            processRow.apply(jsonObject, offset + i);
            res.add(jsonObject);
            i++;
            if(i >= limit) {
                break;
            }
        }

        return res;
    }

    private JsonObject convert(CSVRecord record) {
        Map<String, String> map = record.toMap();
        return JsonUtils.fromMap(map);
    }


}
