import java.io.File;

public class Deleter {
    private String path = new File("").getAbsolutePath();

    //класс, удаляющий файлы

    void delete() {
        for (int i = 0; i < Maximum.MAXIMUM_FILES; i++) {
            String path = "\\file" + String.valueOf(i) + ".txt";
            File newFile = new File(this.path + "\\files" + path);
            newFile.delete();
        }
        File iteration = new File(path + "\\graphData\\iterationData.txt");
        File time = new File(path + "\\graphData\\time.txt");
        File countNumbers = new File(path + "\\graphData\\countNumbers.txt");
        iteration.delete();
        time.delete();
        countNumbers.delete();
    }

}
