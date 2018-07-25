import java.util.Scanner;

public class gradCal {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		/*** Block for Homework ***/
		int[] homeworkMarks = new int[5];
		boolean onTime;
		boolean withIn24h;
		
		for(int i = 0; i < 5; i++) {
			System.out.print("Enter Grade for Homework # " + (i+1) +" --> ");
			homeworkMarks[i] = input.nextInt();
			if(homeworkMarks[i] > 100 || homeworkMarks[i] < 0) {
				System.out.println("Invalid input.");
				i--;
				continue;
			}
			System.out.println("Was Homework # " + (i+1) + " submitted on time?  true\\false");
			onTime = input.nextBoolean();
			if(onTime != true) {
				homeworkMarks[i] = (homeworkMarks[i] / 100) * 75;
				System.out.println("Was Homework # " + (i+1) + " submitted within 24h? true\\false");
				withIn24h = input.nextBoolean();
				if(withIn24h != true) {
					homeworkMarks[i] = (homeworkMarks[i] / 100) * 50;
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			System.out.println(homeworkMarks[i]);
		}
		/*** Block ends ***/
		
		/*** Block for Prelim ***/
		int prelimMarks;
		System.out.print("Enter grade for Prelim -->");
		prelimMarks = input.nextInt();
		while(prelimMarks > 100 || prelimMarks < 0) {
			System.out.println("Invalid entry.");
			System.out.print("Enter grade for Prelim -->");
			prelimMarks = input.nextInt();
		}
		System.out.println(prelimMarks);
		/*** Blocks Ends ***/
		
		/***Block for Final ***/
		int finalMarks;
		System.out.print("Enter grade for Final -->");
		finalMarks = input.nextInt();
		while(finalMarks > 100 || finalMarks < 0) {
			System.out.println("Invalid entry.");
			System.out.print("Enter grade for Final -->");
			finalMarks = input.nextInt();
		}
		System.out.println(finalMarks);
		/*** Block Ends ***/
		
		/*** Block for Quiz ***/
		int[] quizMarks = new int[5];
		
		for(int i = 0; i < 5; i++) {
			System.out.print("Enter Grade for Quiz # " + (i+1) +" --> ");
			quizMarks[i] = input.nextInt();
			if(quizMarks[i] > 10 || quizMarks[i] < 0) {
				System.out.println("Invalid input.");
				i--;
				continue;
			}
		}
		/*** Block Ends ***/
		
		FinalScore finalScoreObj = new FinalScore(homeworkMarks, prelimMarks, finalMarks, quizMarks);
		System.out.println(finalScoreObj.toString());
		System.out.println(finalScoreObj.finalScoreCal());
		System.out.println(finalScoreObj.finalGrade());
	}
}

class HomeworkCal{
	private int[] homeworkMarks = new int[5];
	private int hwFinal;
	
	public HomeworkCal(int[] homeworkMarks) {
		this.homeworkMarks = homeworkMarks;
	}
	
	public int twentyPercent() {
		for(int i = 0; i < 5; i++) {
			hwFinal += homeworkMarks[i]; 
		}
		hwFinal = (hwFinal / 500) * 20; 
		return hwFinal;
	}
	
	@Override
	public String toString() {
		return "\nHomework absolute marks: " + twentyPercent();
	}
}

class prelimCal extends HomeworkCal{
	private int prelimMarks;
	private int prelimFinal;
	
	public prelimCal(int[] homeworkMarks, int prelimMarks) {
		super(homeworkMarks);
		this.prelimMarks = prelimMarks;
	}
	
	public int thirtyPercent() {
		prelimFinal = (prelimMarks / 100) * 30;
		return prelimFinal;
	}
	
	@Override
	public String toString() {
		return "\nPrelim absolute marks: " + thirtyPercent() + " " + super.toString();
	}
}

class FinalCal extends prelimCal{
	private int finalMarks;
	private int finalFinal;
	
	public FinalCal(int[] homeworkMarks, int prelimMarks, int finalMarks) {
		super(homeworkMarks, prelimMarks);
		this.finalMarks = finalMarks;
	}
	
	public int fourtyPercent() {
		finalFinal = (finalMarks / 100) * 40;
		return finalFinal;
	}
	
	@Override
	public String toString() {
		return "\nFinal absolute Marks: " + fourtyPercent() + super.toString();
	}
}

class QuizCal extends FinalCal{
	private int[] quizMarks = new int[5];
	private int quizFinal;
	
	public QuizCal(int[] homeworkMarks, int prelimMarks, int finalMarks, int[] quizMarks) {
		super(homeworkMarks, prelimMarks, finalMarks);
		this.quizMarks = quizMarks;
	}
	
	public int tenPercent() {
		for(int i = 0; i < 5; i++) {
			quizFinal += quizMarks[i]; 
		}
		quizFinal = (quizFinal / 50) * 10; 
		return quizFinal;
	}
	
	@Override
	public String toString() {
		return "\nQuiz absolute Marks: " + tenPercent() + super.toString();
	}
}

class FinalScore extends QuizCal{
	private int finalScore;
	private String finalGrade = "A";

	public FinalScore(int[] homeworkMarks, int prelimMarks, int finalMarks, int[] quizMarks) {
		super(homeworkMarks, prelimMarks, finalMarks, quizMarks);
	}
	
	public int finalScoreCal() {
		finalScore = tenPercent() + twentyPercent() + thirtyPercent() + fourtyPercent();
		System.out.println(finalScore);
		return finalScore;
	}
	
	public String finalGrade() {
		
		if(finalScoreCal() > 90)
			finalGrade = "A";
		else if(finalScoreCal() > 80 && finalScoreCal() < 91)
			finalGrade = "B+";
		else if(finalScoreCal() > 70 && finalScoreCal() < 81)
			finalGrade = "B";
		else if(finalScoreCal() > 60 && finalScoreCal() < 71)
			finalGrade = "C+";
		else if(finalScoreCal() > 50 && finalScoreCal() < 61)
			finalGrade = "C";
		else if(finalScoreCal() > 40 && finalScoreCal() < 51)
			finalGrade = "D+";
		else if(finalScoreCal() > 33 && finalScoreCal() > 41)
			finalGrade = "D";
		else if(finalScoreCal() < 33)
			finalGrade = "F";
		
		return finalGrade;
	}
}
