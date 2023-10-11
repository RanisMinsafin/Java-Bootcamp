package ex05;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] students = new String[10];
        int i = 0;
        for (; i < 10; i++) {
            String name = scanner.next();
            if (name.length() > 10) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            if (name.equals(".")) {
                break;
            }
            students[i] = name;
        }

        String[][] schedule = new String[7][10];
        while (scanner.hasNext()) {
            String time = scanner.next();
            if (time.equals(".")) {
                break;
            }
            String day = scanner.next();
            fillSchedule(schedule, day, time);
        }

        String[][][][] attendance = new String[10][30][10][1];
        while (scanner.hasNext()) {
            String name = scanner.next();
            if (name.equals(".")) {
                break;
            }
            scanner.next();
            String date = scanner.next();
            String status = scanner.next();
            System.out.println("status" + status);
            for (i = 0; i < students.length && !students[i].equals(name); i++) ;
            attendance[i][Integer.parseInt(date) - 1][0][0] = status;
        }

        for (i = 0; i <= 30; i++) {
            printSchedule(i, schedule);
        }
        System.out.println();

        for (i = 0; i < students.length && students[i] != null; i++) {
            System.out.printf("%10s", students[i]);
            printAttendance(attendance[i], schedule);
        }
    }

    private static void fillSchedule(String[][] schedule, String day, String time) {
        switch (day) {
            case "MO":
                schedule[0][0] = time;
                schedule[0][1] = day;
                break;
            case "TU":
                schedule[1][0] = time;
                schedule[1][1] = day;
                break;
            case "WE":
                schedule[2][0] = time;
                schedule[2][1] = day;
                break;
            case "TH":
                schedule[3][0] = time;
                schedule[3][1] = day;
                break;
            case "FR":
                schedule[4][0] = time;
                schedule[4][1] = day;
                break;
            case "SA":
                schedule[5][0] = time;
                schedule[5][1] = day;
                break;
            case "SU":
                schedule[6][0] = time;
                schedule[6][1] = day;
                break;
            default:
                System.err.println("Invalid day: " + day);
                System.exit(-1);
        }
    }

    private static void printSchedule(int i, String[][] schedule) {
        int j = 0;
        if (i == 0) {
            System.out.print("          ");
        }
        int day = ++i % 7;
        while (j < 1 && schedule[day][j] != null) {
            System.out.print(schedule[day][j] + ":00 ");
            switch (day) {
                case 0:
                    System.out.printf("MO %2d|", i);
                    break;
                case 1:
                    System.out.printf("TU %2d|", i);
                    break;
                case 2:
                    System.out.printf("WE %2d|", i);
                    break;
                case 3:
                    System.out.printf("TH %2d|", i);
                    break;
                case 4:
                    System.out.printf("FR %2d|", i);
                    break;
                case 5:
                    System.out.printf("SA %2d|", i);
                    break;
                case 6:
                    System.out.printf("SU %2d|", i);
                    break;
            }
            j++;
        }
    }

    private static void printAttendance(String[][][] attendance, String[][] schedule) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 1 && schedule[(i + 1) % 7][j] != null; j++) {
                if (attendance[i][j][0] != null && attendance[i][j][0].equals("HERE")) {
                    System.out.printf("        %2d|", 1);
                } else if (attendance[i][j][0] != null && attendance[i][j][0].equals("NOT_HERE")) {
                    System.out.printf("        %2d|", -1);
                } else {
                    System.out.print("          |");
                }
            }
        }
        System.out.println();
    }
}