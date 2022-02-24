package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private int imageWidth = 320;
    private int imageHeight = 240;
    private BufferedImage bufferedImage;
    public Scanner sc;
    private boolean hasPainted = false;

    public static void main(String[] args)
    {
        // write your code here
        System.out.println("Welcome to Bdobe Fotobutik CS69, there are no copyright infringements here.");
        Main main = new Main();
        main.bufferedImage = new BufferedImage(main.imageWidth, main.imageHeight, BufferedImage.TYPE_INT_RGB);
        main.sc = new Scanner(System.in);
        // draw examples of functions
        main.drawExamples(); // set black background - its also black by default, so kind of irrelevant
        // drawings - go on, make some. it's the most user-friendly image editor in the world
        main.drawGrinningFace(10, 80);

        main.writeImageToPNG("image");

        main.wantToPaint();
    }
    private void wantToPaint()
    {
        boolean answeredYesNo = false;
        while (!answeredYesNo)
        {
            if (!hasPainted)
            {
                System.out.print(ANSI_BLUE + "Do you want to paint? (" + ANSI_RED + "Y" + ANSI_BLUE + "/" + ANSI_RED + "N" + ANSI_BLUE + "): " + ANSI_RESET);
            }
            else
            {
                System.out.print(ANSI_BLUE + "Do you want to paint again? (" + ANSI_RED + "Y" + ANSI_BLUE + "/" + ANSI_RED + "N" + ANSI_BLUE + "): " + ANSI_RESET);
            }
            String yesNo = sc.nextLine();

            if ((yesNo.equals("Y")) || (yesNo.equals("y")))
            {
                System.out.println(ANSI_BLUE + "Ok. Good luck." + ANSI_RESET);
                answeredYesNo = true;
                hasPainted = true;
                fillImage(255, 255, 255);
                writeImageToPNG("image");
                toolPicker();
            }
            else if ((yesNo.equals("N")) || (yesNo.equals("n")))
            {
                System.out.println(ANSI_RED + "Me too, this is not exactly the most user friendly image editor. Let's GTFO." + ANSI_RESET);
                answeredYesNo = true;
            }
            if (!answeredYesNo)
            {
                System.out.println(ANSI_YELLOW + "You can only enter '" + ANSI_RED + "Y" + ANSI_YELLOW + "' or '" + ANSI_RED + "N" + ANSI_YELLOW + "'. You entered: " + ANSI_RED + yesNo + ANSI_YELLOW + ". Try again." + ANSI_RESET);
            }
        }
    }
    private void toolPicker()
    {
        System.out.println(ANSI_BLUE + "Enter" + ANSI_RESET + " a " + ANSI_RED + "Tool" + ANSI_RESET + " to use:");
        System.out.println(ANSI_BLUE + "L" + ANSI_RESET + ": " + ANSI_RED + "Line Tool" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Q" + ANSI_RESET + ": " + ANSI_RED + "Quit Drawing" + ANSI_RESET);

        boolean answeredTool = false;
        while (!answeredTool)
        {
            System.out.print(ANSI_BLUE + "Enter" + ANSI_RESET + " a " + ANSI_RED + "Tool" + ANSI_RESET + ": ");
            String answer = sc.nextLine();

            if ((answer.equals("L")) || (answer.equals("l")))
            {
                System.out.println(ANSI_BLUE + "Ok. Initiating " + ANSI_RED + "Line Tool" + ANSI_BLUE + "." + ANSI_RESET);
                answeredTool = true;
                drawLineTool();
            }
            else if ((answer.equals("Q")) || (answer.equals("q")))
            {
                System.out.println(ANSI_BLUE + "I get it, it's an awful image editor." + ANSI_RESET);
                answeredTool = true;
            }
            if (!answeredTool)
            {
                System.out.println(ANSI_YELLOW + "You can only enter '" + ANSI_RED + "L" + ANSI_YELLOW + "' or '" + ANSI_RED + "Q" + ANSI_YELLOW + "'. You entered: " + ANSI_RED + answer + ANSI_YELLOW + ". Try again." + ANSI_RESET);
            }
        }
    }
    private void drawLineTool()
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        System.out.print(ANSI_RED + "Enter red value (0-255): " + ANSI_RESET);
        int red = sc.nextInt();
        System.out.print(ANSI_GREEN + "Enter green value (0-255): " + ANSI_RESET);
        int green = sc.nextInt();
        System.out.print(ANSI_BLUE + "Enter blue value (0-255): " + ANSI_RESET);
        int blue = sc.nextInt();
        System.out.print(ANSI_YELLOW + "Enter first x-coordinate (from 0 to " + imageWidth + "): " + ANSI_RESET);
        x1 = sc.nextInt();
        System.out.print(ANSI_YELLOW + "Enter first y-coordinate (from 0 to " + imageHeight + "): " + ANSI_RESET);
        y1 = sc.nextInt();

        boolean isFinishedDrawingLine = false;
        while (!isFinishedDrawingLine)
        {
            System.out.print(ANSI_YELLOW + "Enter next x-coordinate from previous position (from " + (0-x1) + " to " + (imageWidth-x1) + "): " + ANSI_RESET);
            x2 = sc.nextInt();
            System.out.print(ANSI_YELLOW + "Enter next y-coordinate from previous position (from " + (0-y1) + " to " + (imageHeight-y1) + "): " + ANSI_RESET);
            y2 = sc.nextInt();
            drawLine(x1, y1, x2, y2, red, green, blue);
            writeImageToPNG("image");
            x1 = x2;
            y1 = y2;
            boolean answeredYesNo = false;
            while (!answeredYesNo)
            {
                System.out.print(ANSI_BLUE + "Do you want to continue the line from this point? (" + ANSI_RED + "Y" + ANSI_BLUE + "/" + ANSI_RED + "N" + ANSI_BLUE + "): " + ANSI_RESET);
                String yesNo = sc.next();

                if ((yesNo.equals("Y")) || (yesNo.equals("y")))
                {
                    System.out.println(ANSI_BLUE + "Ok. Continuing drawing line." + ANSI_RESET);
                    answeredYesNo = true;
                }
                else if ((yesNo.equals("N")) || (yesNo.equals("n")))
                {
                    System.out.println(ANSI_RED + "Ok. Going back to Tool Picker." + ANSI_RESET);
                    answeredYesNo = true;
                    isFinishedDrawingLine = true;
                    toolPicker();
                }
                if (!answeredYesNo)
                {
                    System.out.println(ANSI_YELLOW + "You can only enter '" + ANSI_RED + "Y" + ANSI_YELLOW + "' or '" + ANSI_RED + "N" + ANSI_YELLOW + "'. You entered: " + ANSI_RED + yesNo + ANSI_YELLOW + ". Try again." + ANSI_RESET);
                }
            }
        }
    }
    private void writeImageToPNG(String name)
    {
        try
        {
            ImageIO.write(bufferedImage, "png", new File(name + ".png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void drawExamples()
    {
        fillImage(0, 0, 0); // set black background - its also black by default, so kind of irrelevant
        drawPixelDot(10, 10, 255, 130, 255); // draw a single pixel
        fillRect(20, 10, 40, 20, 255, 0, 0); // draw rectangle and fill color
        drawRect(70, 10, 40, 20, 0, 255, 0); // draw rectangle
        lineRect(120, 10, 40, 20, 5, 255, 255, 0); // draw rectangle with line thickness
        drawLine(170, 10, 210, 30, 0, 0, 255); // draw a line from x1, y1 to x2, y2
    }
    private void drawPixelDot(int xPos, int yPos, int red, int green, int blue)
    {
        bufferedImage.setRGB(xPos, yPos, red*65536 + green*256 + blue);
    }
    private void fillImage(int red, int green, int blue)
    {
        for (int i = 0; i < imageWidth; i++)
        {
            for (int j = 0; j < imageHeight; j++)
            {
                drawPixelDot(i, j, red, green, blue);
            }
        }
    }
    private void fillRect(int xPos, int yPos, int width, int height, int red, int green, int blue)
    {
        for (int i = xPos; i <= xPos+width; i++)
        {
            for (int j = yPos; j <= yPos+height; j++)
            {
                drawPixelDot(i, j, red, green, blue);
            }
        }
    }
    private void drawRect(int xPos, int yPos, int width, int height, int red, int green, int blue)
    {
        for (int i = xPos; i <= xPos+width; i++)
        {
            for (int j = yPos; j <= yPos+height; j++)
            {
                if ((i == xPos) || (i == xPos+width) || (j == yPos) || (j == yPos+height))
                {
                    drawPixelDot(i, j, red, green, blue);
                }
            }
        }
    }
    private void lineRect(int xPos, int yPos, int width, int height, int thickness, int red, int green, int blue)
    {
        for (int i = xPos; i <= xPos+width; i++)
        {
            for (int j = yPos; j <= yPos+height; j++)
            {
                if (((i >= xPos) && (i <= xPos+thickness)) || ((i >= xPos+width-thickness) && (i <= xPos+width)) || ((j >= yPos) && (j <= yPos+thickness)) || ((j >= yPos+height-thickness) && (j <= yPos+height)))
                {
                    drawPixelDot(i, j, red, green, blue);
                }
            }
        }
    }
    public void drawLine(int x1, int y1, int x2, int y2, int red, int green, int blue)
    {
        double x, y, dx, dy, step;
        int i;

        dx = (x2 - x1);
        dy = (y2 - y1);
        if (Math.abs(dx) >= Math.abs(dy))
        {
            step = Math.abs(dx);
        }
        else
        {
            step = Math.abs(dy);
        }
        dx = dx / step;
        dy = dy / step;
        x = x1;
        y = y1;
        i = 1;
        while (i <= step) {
            drawPixelDot((int)x, (int)y, red, green, blue);
            x = x + dx;
            y = y + dy;
            i = i + 1;
        }
    }
    public void drawGrinningFace(int xPos, int yPos)
    {
        // face
        fillRect(xPos, yPos, 200, 100, 200, 135, 80);
        // mouth left side
        drawLine(xPos+10, yPos+60, xPos+30, yPos+70, 255, 0, 0);
        drawLine(xPos+30, yPos+70, xPos+50, yPos+75, 255, 0, 0);
        drawLine(xPos+50, yPos+75, xPos+80, yPos+80, 255, 0, 0);
        drawLine(xPos+80, yPos+80, xPos+100, yPos+82, 255, 0, 0);
        // mouth middle
        drawLine(xPos+100, yPos+82, xPos+110, yPos+82, 255, 0, 0);
        // mouth right side
        drawLine(xPos+110, yPos+82, xPos+130, yPos+80, 255, 0, 0);
        drawLine(xPos+130, yPos+80, xPos+160, yPos+75, 255, 0, 0);
        drawLine(xPos+160, yPos+75, xPos+170, yPos+70, 255, 0, 0);
        drawLine(xPos+170, yPos+70, xPos+190, yPos+60, 255, 0, 0);
        // right eyebrow
        drawLine(xPos+45, yPos+10, xPos+70, yPos+15, 0, 0, 0);
        // left eyebrow
        drawLine(xPos+155, yPos+10, xPos+125, yPos+15, 0, 0, 0);
        // right eye
        fillRect(xPos+50, yPos+20, 20, 20, 255, 255, 255);
        lineRect(xPos+50, yPos+20, 20, 20, 2, 135, 25, 55);
        drawPixelDot(xPos+60, yPos+30, 255, 0, 0);
        // left eye
        fillRect(xPos+130, yPos+20, 20, 20, 255, 255, 255);
        lineRect(xPos+130, yPos+20, 20, 20, 2, 135, 25, 55);
        drawPixelDot(xPos+140, yPos+30, 255, 0, 0);
    }
}
