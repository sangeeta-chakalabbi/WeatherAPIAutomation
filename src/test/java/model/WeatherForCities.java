package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class WeatherForCities{
    public int count;
    public ArrayList<WeatherForACity> data;
}
