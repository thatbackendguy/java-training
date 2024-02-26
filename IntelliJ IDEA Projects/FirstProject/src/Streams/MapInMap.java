import java.util.ArrayList;
import java.util.HashMap;

public class MapInMap
{
    public static void main(String[] args)
    {
        var map = new HashMap<>();

        var yashMarks = new ArrayList<>();
        var shivamMarks = new ArrayList<>();

        yashMarks.add(78);
        yashMarks.add(88);
        yashMarks.add(68);
        yashMarks.add(89);

        shivamMarks.add(88);
        shivamMarks.add(86);
        shivamMarks.add(89);
        shivamMarks.add(85);

        map.put("yash", yashMarks);
        map.put("shivam", shivamMarks);

        map.forEach((k, v) -> {
            ArrayList<Integer> value = (ArrayList<Integer>) v;
            if(k.equals("shivam"))
            {
                value.add(90);
            }
        });
        System.out.println(map);
    }
}
