import java.util.*;
public class Main {
    public static void main(String[] args){
        IntPair[][] payoff=new IntPair[2][2];


         payoff[0][0]=new IntPair(2,1);
         payoff[0][1]=new IntPair(2,1);
         payoff[1][0]=new IntPair(0,0);
         payoff[1][1]=new IntPair(1,2);



         Payoff p=new Payoff(payoff);


        Scanner sc=new Scanner(System.in);
        int rep3 = 0;




        do
        {
            p.afficher_menu();
            rep3=Integer.parseInt(sc.nextLine());
            switch(rep3)
            {
                case 1:
                    p.PureNashEquilibria();
                    p.Afficher();
                    p.AfficherPure();
                    break;
                case 2:
                    p.MixedNashEquilibria();
                    break;
                case 3:
                    break;
                default:System.out.println("Veuillez respecter le menu!");
            }
        }while(rep3!=3);

    }
}
