package Board.fileManagement;

import java.io.*;

public class ReadPointsByName {
    String resName;

    public ReadPointsByName(String resName) {
        this.resName = resName;
    }

    public String ExtractPointsToString(String name) {
        String points = "";
        try(final InputStream is = getClass().getClassLoader().getResourceAsStream(resName)) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            String nameOfNumbers;

            while (line != null) {
                nameOfNumbers = line.split(":")[0];
                if (nameOfNumbers.equals(name)) {
                    points = (line.split(":")[1]).trim();
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    public float[] ExtractPointsToFloatArray(String name) {
        return StringToFloatArray(ExtractPointsToString(name));
    }

    public int[] ExtractPointsToIntArray(String name) {
        return StringToIntArray(ExtractPointsToString(name));
    }

    public float[] StringToFloatArray(String points) {
        String strArr[] = points.split(", ");
        float pointsAsFloatArray[] = new float[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            pointsAsFloatArray[i] = Float.parseFloat(strArr[i]);
        }
        return pointsAsFloatArray;
    }

    public int[] StringToIntArray(String points) {
        String strArr[] = points.split(", ");
        int pointsAsIntArray[] = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            pointsAsIntArray[i] = Integer.parseInt(strArr[i]);
        }
        return pointsAsIntArray;
    }

}
