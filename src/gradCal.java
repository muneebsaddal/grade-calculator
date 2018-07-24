import java.util.Scanner;

public class gradCal {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		/*** Block for Homework ***/
		double[] homeworkMarks = new double[5];
		boolean onTime;
		boolean withIn24h;
		
		for(int i = 0; i < 5; i++) {
			System.out.print("Enter Grade for Homework # " + (i+1) +" --> ");
			homeworkMarks[i] = input.nextDouble();
			if(homeworkMarks[i] > 100 || homeworkMarks[i] < 0) {
				System.out.println("Invalid input.");
				i--;
				continue;
			}
			System.out.println("Was Homework # " + (i+1) + " submitted on time?  true\\false");
			onTime = input.nextBoolean();
			if(onTime != true) {
				homeworkMarks[i] = homeworkMarks[i] * 0.75;
				System.out.println("Was Homework # " + (i+1) + " submitted within 24h? true\\false");
				withIn24h = input.nextBoolean();
				if(withIn24h != true) {
					homeworkMarks[i] = homeworkMarks[i] * 0.5;
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			System.out.println(homeworkMarks[i]);
		}
		/*** Block ends ***/
		
		/*** Block for Prelim ***/
		double prelimMarks;
		System.out.print("Enter grade for Prelim -->");
		prelimMarks = input.nextDouble();
		while(prelimMarks > 100 || prelimMarks < 0) {
			System.out.println("Invalid entry.");
			System.out.print("Enter grade for Prelim -->");
			prelimMarks = input.nextDouble();
		}
		System.out.println(prelimMarks);
		/*** Blocks Ends ***/
		
		/***Block for Final ***/
		double finalMarks;
		System.out.print("Enter grade for Final -->");
		finalMarks = input.nextDouble();
		while(finalMarks > 100 || finalMarks < 0) {
			System.out.println("Invalid entry.");
			System.out.print("Enter grade for Final -->");
			finalMarks = input.nextDouble();
		}
		System.out.println(finalMarks);
		/*** Block Ends ***/
		
		/*** Block for Quiz ***/
		double[] quizMarks = new double[5];
		
		for(int i = 0; i < 5; i++) {
			System.out.print("Enter Grade for Quiz # " + (i+1) +" --> ");
			quizMarks[i] = input.nextDouble();
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
	private double[] homeworkMarks = new double[5];
	private double hwFinal;
	
	public HomeworkCal(double[] homeworkMarks) {
		this.homeworkMarks = homeworkMarks;
	}
	
	public double twentyPercent() {
		for(int i = 0; i < 5; i++) {
			hwFinal += homeworkMarks[i]; 
		}
		return (hwFinal / 500) * 20;
	}
	@Override
	public String toString() {
		return "\nHomework absolute marks: " + twentyPercent();
	}
}

class prelimCal extends HomeworkCal{
	private double prelimMarks;
	private double prelimFinal;
	
	public prelimCal(double[] homeworkMarks, double prelimMarks) {
		super(homeworkMarks);
		this.prelimMarks = prelimMarks;
	}
	
	public double thirtyPercent() {
		return (prelimFinal / 100) * 30;
	}
	@Override
	public String toString() {
		return "\nPrelim absolute marks: " + thirtyPercent() + super.toString();
	}
}

class FinalCal extends prelimCal{
	private double finalMarks;
	private double finalFinal;
	
	public FinalCal(double[] homeworkMarks, double prelimMarks, double finalMarks) {
		super(homeworkMarks, prelimMarks);
		this.finalMarks = finalMarks;
	}
	
	public double fourtyPercent() {
		return (finalFinal / 100) * 40;
	}
	@Override
	public String toString() {
		return "\nFinal absolute Marks: " + fourtyPercent() + super.toString();
	}
}

class QuizCal extends FinalCal{
	private double[] quizMarks = new double[5];
	private double quizFinal;
	
	public QuizCal(double[] homeworkMarks, double prelimMarks, double finalMarks, double[] quizMarks) {
		super(homeworkMarks, prelimMarks, finalMarks);
		this.quizMarks = quizMarks;
	}
	
	public double tenPercent() {
		return (quizFinal / 50) * 10;
	}
	@Override
	public String toString() {
		return "\nQuiz absolute Marks: " + tenPercent() + super.toString();
	}
}

class FinalScore extends QuizCal{
	private double finalScore;
	private String finalGrade = "B";

	public FinalScore(double[] homeworkMarks, double prelimMarks, double finalMarks, double[] quizMarks) {
		super(homeworkMarks, prelimMarks, finalMarks, quizMarks);
	}
	
	public double finalScoreCal() {
		finalScore = tenPercent() + twentyPercent() + thirtyPercent() + fourtyPercent();
		return finalScore;
	}
	
	public String finalGrade() {
		return finalGrade;
	}
}
