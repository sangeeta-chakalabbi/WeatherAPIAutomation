package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class WeatherForACity{
    public double app_temp;
    public int aqi;
    public String city_name;
    public int clouds;
    public String country_code;
    public String datetime;
    public double dewpt;
    public double dhi;
    public double dni;
    public double elev_angle;
    public double ghi;
    public double gust;
    public double h_angle;
    public String lat;
    public String lon;
    public String ob_time;
    public String pod;
    public int precip;
    public double pres;
    public int rh;
    public double slp;
    public int snow;
    public double solar_rad;
    public String state_code;
    public String station;
    public ArrayList<String> sources;
    public String sunrise;
    public String sunset;
    public double temp;
    public String timezone;
    public int ts;
    public double uv;
    public int vis;
    public Weather weather;
    public String wind_cdir;
    public String wind_cdir_full;
    public int wind_dir;
    public double wind_spd;
}
