package com.batch.practice.domain.converter;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;

/**
 * Created by kanghonggu on 2017. 3. 21..
 */
@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<DateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(DateTime dateTime) {
        return dateTime != null ? new Timestamp(dateTime.getMillis()) : null;
    }

    @Override
    public DateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp != null ? new DateTime(timestamp, DateTimeZone.UTC) : null;
    }
}