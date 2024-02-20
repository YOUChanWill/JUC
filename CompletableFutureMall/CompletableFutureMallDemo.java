import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class CompletableFutureMallDemo {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(11).setStudentName("z3").setMajor("english"); //链式调用
        System.out.println(student.getMajor());
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)//开启链式调用
@Data
class Student {
    private Integer id;
    private String studentName;
    private String major;

}
