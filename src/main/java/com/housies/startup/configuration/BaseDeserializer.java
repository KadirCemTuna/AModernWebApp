package com.housies.startup.configuration;

import com.housies.startup.model.Customer;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

public class BaseDeserializer implements Deserializer<Object> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Customer deserialize(String s, byte[] bytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(bis);
            return (Customer) objectInputStream.readObject();

        }catch (Exception e){
            e.printStackTrace();
        }

        return new Customer();
    }

    @Override
    public void close() {

    }

}
