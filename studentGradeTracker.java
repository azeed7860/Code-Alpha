import java.util.Scanner;

public class studentGradeTracker {
    public static void main(String[] agrs){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of students:-");
        int numStudent=sc.nextInt();

        int[] grades=new int[numStudent];

        //Enter students grades
        for(int i=0;i<numStudent;i++){
            System.out.println("Enter the grade for student "+(i+1)+":");
            grades[i]= sc. nextInt();
        }

        double avarage=calculateAvg(grades);
        int highscore=calculateHighScore(grades);
        int lowScore=calculateLowScore(grades);

        System.out.println("Avarage Score:-"+avarage);
        System.out.println("Highest Score:-"+highscore);
        System.out.println("Lowest Score:-"+lowScore);

        sc.close();
        
    }

    // Calculate Avarage grades of students

    private static double calculateAvg(int[] grades){
        int sum=0;
        for(int grade : grades){
            sum += grade;
        }
        return (double)sum/grades.length;
    }

    // Calculate highest grade of students
    private static int calculateHighScore(int[] grades){
        int highest=grades[0];
        for(int grade : grades){
            if(grade > highest){
                highest=grade;
            }
        }
        return highest;
    }

    // Calculate lowest grade of students
    private static int calculateLowScore(int[] grades){
        int lowest=grades[0];
        for(int grade : grades){
            if(grade< lowest){
                lowest=grade;
            }
        }
        return lowest;
    } 
}
