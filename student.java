package cn.org.glimmer.Java03;


import java.util.*;
import java.util.stream.Collectors;


public class student {
    private String name;
    private Map<String,Integer> subjects;
    //创建成员
    public void addnum(String subject,int num){   				subjects.put
            (subject,subjects.getOrDefault(subject,0)+num);
    }
    //构建加值方法，使输入直接加到value当中
    public static void main(String[] args) {
        student xiaoming = new student();
        student xiaohong = new student();
        xiaoming.subjects = new HashMap<>();
        xiaohong.subjects = new HashMap<>();
        xiaoming.name = ("小明");
        xiaohong.name = ("小红");
        String initialdata = "math:5,English:10,Chinese:10,math:20,English:10,chemistry:30,math:10,math:20";
        Arrays.stream(initialdata.split(","))
                //创建stream并切除“，”
                .map(a -> a.split(":"))
                //建立映射使用a接收切除“：”后划分出的集合
                .forEach(a -> xiaoming.addnum(a[0], Integer.parseInt(a[1])));
        //对元素分别使用加值方法，一个key一个value
        int ans = 1;//声明循环判断变量
        do {
            System.out.println("请输入学生 科目 错题数目：");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            List<String> initialadd = Arrays.stream(str.split(" ")).toList();
            //收集输入并切分装入List
            String stu = initialadd.get(0);
            //第一个元素是学生
            if (stu.equals(xiaoming.name)) {

                if (initialadd.size() == 3)
                //判断输入是否总共三个值，防止报错
                {
                    xiaoming.addnum(initialadd.get(1), Integer.parseInt(initialadd.get(2)));
                }
                Map<String,Integer> sorteddata = xiaoming.subjects.entrySet()
                        .stream()
                        //键值对做成stream
                        .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                        //利用Map。Entry中的排序
                        //reversed是倒序
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                //分别用getKey和getValue得到两个map,后续必须合成并接收
                                (e1, e2) -> e1, LinkedHashMap::new));
                //把刚才的两个map合成一个map并保存在一个LinkedHashmap中

                System.out.println("小明的错题数目从高到低依次为：");
                System.out.println(sorteddata);
                //输出排序数组
                System.out.println("按0继续输入：");
                ans = input.nextInt();
            }
            else if (stu.equals(xiaohong.name)) {
                if (initialadd.size() == 3) {
                    xiaohong.addnum(initialadd.get(1), Integer.parseInt(initialadd.get(2)));
                    //排序原理同上,不再做注释(眼睛看花了)
                    Map<String,Integer> sorteddata = xiaohong.subjects.entrySet()
                            .stream()
                            .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                    (e1, e2) -> e1, LinkedHashMap::new));

                    System.out.println("小红的错题数目从高到低依次为：");
                    System.out.println(sorteddata);
                    //输出排序数组
                    System.out.println("按0继续输入：");
                    ans = input.nextInt();
                }
                else {
                    System.out.println("不存在小红的数据");
                    System.out.println("按0重新输入：");
                    ans = input.nextInt();
                }

            }
            else{
                System.out.println("您输入的内容有误");
                System.out.println("按0重新输入：");
                ans = input.nextInt();

            }

        }
        while (ans == 0);//循环判断
        System.out.println("退出系统");
    }
}

