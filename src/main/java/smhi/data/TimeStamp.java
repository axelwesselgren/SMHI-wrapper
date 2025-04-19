package smhi.data;

import smhi.interfaces.Parameter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;

public class Timestamp {
    private final LocalDateTime time;
    private final HashMap<Parameter, Double> parameters;

    public Timestamp(LocalDateTime validTime) {
        this.time = validTime;
        this.parameters = new HashMap<>();
    }

    public void addParameter(Parameter parameter, double value) {
        parameters.put(parameter, value);
    }
    public void addParameters(HashMap<Parameter, Double> parameters) {
        this.parameters.putAll(parameters);
    }

    public HashMap<Parameter, Double> getParameters() {
        return parameters;
    }

    public String getDate() {
        return time.toLocalDate().toString();
    }
    public String getTime() {
        return time.toLocalTime().toString();
    }

    @Override
    public String toString() {
        String params = "";

        for (Entry<Parameter, Double> entry : parameters.entrySet()) {
            params += entry.getKey().getDesc() + ": " + entry.getValue() + "\n";
        }

        return "Time: " + time + "\n" + params;
    }
}
