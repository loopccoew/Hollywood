/*Multiplayer Hollywood game with 2 classes */

package hollywood;
import java.util.*;

class challenger
{
    Scanner s=new Scanner(System.in);
    String select;//question
    String guess;//answer
    int y;//no. of consonants
    int x;//index of movie in array
    char[] already=new char[26];//to store already guessed alphabets
   
    challenger()
    {
        select="";
        guess="";
        for(int i=0;i<26;i++)
        {
        	already[i]=0;//frequency of every alphabet is zero now
        }
    }
  
    //or you can have a large database of movies.
    String name[]= {"lagaan","sing street","lion","la la land"};

    void getmovie()//either serially assign a movie to every player. Or ask for a number and select movie at that index for the player
    {
        System.out.println("Select a random number between 0-3");
        x=s.nextInt();
        select=name[x];
      
        for(int i=0;i<select.length();i++)
        {
            char ch=select.charAt(i);
          
            if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')
            {
                System.out.print(ch);
                guess=guess+ch;
            }
          
            else if(ch==' ')
            {
                System.out.print("/");
                guess=guess+'/';
            }
          
            else
            {
                System.out.print("-");
                guess+='-';
                y++;//count of consonants/no. of occurrences of consonants
            }
        }
    }
  
    void hint()
    {
        switch(x)
        {
            case 0:System.out.println("British, cricket");
                    break;
            case 1:System.out.println("Music, school band");
                    break;
            case 2:System.out.println("Australia, lost kid");
                    break;
            case 3:System.out.println("Wannabe musician, actress, fools who dream");
                    break;
        }
    }
  
    void display(char ch, int i)
    {
        guess=guess.substring(0, (i))+ch+guess.substring(i+1);
        System.out.println(guess);
        y--;//game should run till y=0 i.e. all consonants are solved
      
    }
  
    void play()
    {
      
        int chances=9;//game should run till all chances are lost
        int hints=0;//to keep record of hints taken
        int value=0;//to store ascii value of char
        do
        {
            //hints
            System.out.println("\nDo you want a hint? Press Y for yes");
            char yes=s.next().charAt(0);
            if((yes=='y'||yes=='Y')&&hints==0)
            {
                hint();
                hints++;              
            }
            else if(hints>0&&(yes=='y'||yes=='Y'))
                System.out.println("Sorry hints exhausted");
          
          
            //guess
            System.out.println("\nEnter your guess (all vowels displayed already)");//assuming user always enters in lowercase, otherwise use tolowercase
            char ch=s.next().charAt(0);
            value=(int)ch;
            value=value-97;//to correlate with index of array
            already[value]++;
            
            if(already[value]==1||already[value]==0)//i.e. char guessed once or never guessed
            {
          
	            int flag=0;//initializes to zero for every character
	            if((ch!='a'&&ch!='e'&&ch!='i'&&ch!='o'&&ch!='u'))
	            {
	                for(int i=0;i<select.length();i++)
	                {
	                    char c=select.charAt(i);
	                    //System.out.println(c);
	                    if((ch==c))
	                    {
	                        display(ch,i);
	                        flag=1;
	                    }
	                }
	            }
	            else
	            {
	                System.out.println("Vowels already displayed!");
	                flag=2;//so that it doesn't go into wrong guess
	            }
          
	            //wrong guess
	            if(flag==0)
	            {
	                System.out.println("Wrong guess :(");
	                chances--;
	                System.out.println("No. of guesses left="+chances);
	            }
            }
            else
            	System.out.println("Already guessed the alphabet");
                 
          
        }while(chances!=0&&y!=0);
      
        //checking if won or not
        if(chances==0&&y!=0)
        {
            System.out.println("You lost");
        }
      
        else if(chances!=0&&y==0)
        {
            System.out.println("You won");
        }
    }
}

class hollywood
{
    public static void main(String[] args)
    {
    	
    	
        Scanner sc=new Scanner(System.in);
        
        System.out.println("HOLLYWOOD\nHow to play? A movie name will be selected and only vowels will be displayed(- for consonants and / for spaces)\nYou have to guess the missing characters to find out the movie name");
        System.out.println("How many players?");
        int players=sc.nextInt();
      
        challenger c[]=new challenger[players];//array of challenger class array
      
        for(int i=0;i<players;i++)
        {
            System.out.println("Player "+(i+1));
            c[i]=new challenger();
            c[i].getmovie();//movie is assigned to player and displayed on screen
            c[i].play();
        }
      
    }
}
