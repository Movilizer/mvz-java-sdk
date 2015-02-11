package com.movilizer.container;

import com.google.gson.JsonObject;
import com.movilitas.movilizer.v11.MovilizerGenericDataContainer;
import com.movilitas.movilizer.v11.MovilizerGenericDataContainerEntry;
import com.movilitas.movilizer.v11.MovilizerGenericUploadDataContainer;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.List;

import static com.movilizer.util.json.JsonUtils.toJsonObject;


/**
 * @author Peter.Grigoriev@gmail.com.
 */
public class MovilizerJsonContainer implements IMovilizerJsonContainer {
    private final String key;
    private final String deviceAddress;
    private final String moveletKey;
    private final Date creationDate;
    private final JsonObject data;

    public MovilizerJsonContainer(String key, String deviceAddress, String moveletKey, Date creationDate, JsonObject data) {

        this.key = key;
        this.deviceAddress = deviceAddress;
        this.moveletKey = moveletKey;
        this.creationDate = creationDate;
        this.data = data;
    }

    public static IMovilizerJsonContainer fromUploadDataContainer(MovilizerGenericUploadDataContainer container) {
        String deviceAddress = container.getDeviceAddress();
        String key = container.getKey();
        String moveletKey = container.getMoveletKey();
        XMLGregorianCalendar creationTimestamp = container.getCreationTimestamp();
        Date creationDate = new Date(creationTimestamp.getMillisecond());

        MovilizerGenericDataContainer data = container.getData();
        if(data == null) {
            return null;
        }
        List<MovilizerGenericDataContainerEntry> entries = data.getEntry();
        if(entries == null || entries.isEmpty()) {
            return null;
        }

        String value = entries.get(0).getValstr();
        JsonObject jsonObject = toJsonObject(value);
        return new MovilizerJsonContainer(key, deviceAddress, moveletKey, creationDate, jsonObject);

    }


    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getDeviceAddress() {
        return deviceAddress;
    }

    @Override
    public String getMoveletKey() {
        return moveletKey;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public JsonObject getData() {
        return data;
    }
}
