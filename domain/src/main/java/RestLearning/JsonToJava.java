package RestLearning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJava {
    public static void main (String[] args) {
        String jsonStr = " {\n" +
                "    \"emp_id\":1,\n" +
                "    \"name\":\"Marina\",\n" +
                "    \"cmpName\":\"TATA\"\n" +
                " },";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Employee employee = objectMapper.readValue(jsonStr, Employee.class);
            System.out.println("Id = " + employee.getEmpId() + " and name = " + employee.getName());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
