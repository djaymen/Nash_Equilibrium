import java.util.Arrays;

public class Payoff {
    private IntPair[][] payoff=new IntPair[2][2];
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_D = "\u001B[32m";
    public static final String COLOR_E = "\u001B[33m";
    public static final String COLOR_C = "\u001B[36m";



    public Payoff(IntPair[][] pf){
        this.payoff=pf;

    }
    void AfficherPure(){
        System.out.println("\n"+COLOR_D+"---------------------------------------------------------------------"+COLOR_RESET);

        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                if (this.payoff[i][j].isSecondplayerMax && this.payoff[i][j].isFirstplayerMax){
                    System.out.println(this.payoff[i][j]);
                }

            }
        }
        System.out.println("\n"+COLOR_D+"---------------------------------------------------------------------"+COLOR_RESET);

    }
    public static String toFraction(double d) {
        if (d==0) return "0";
        StringBuilder sb = new StringBuilder();
        if (d < 0) {
            sb.append('-');
            d = -d;
        }
        long l = (long) d;
        if (l != 0) sb.append(l);
        d -= l;
        double error = Math.abs(d);
        int bestDenominator = 1;
        for(int i=2;i<=1000;i++) {
            double error2 = Math.abs(d - (double) Math.round(d * i) / i);
            if (error2 < error) {
                error = error2;
                bestDenominator = i;
            }
        }
        if (bestDenominator > 1)
            sb.append(' ').append(Math.round(d * bestDenominator)).append('/') .append(bestDenominator);
        return sb.toString();
    }

    public boolean isInfinityQ(){

        if(this.payoff[0][0].secondPlayer==this.payoff[1][0].secondPlayer && this.payoff[0][1].secondPlayer==this.payoff[1][1].secondPlayer )
            return true;
        return false;
    }
    public boolean isInfinityP(){

        if(this.payoff[0][0].firstPlayer==this.payoff[0][1].firstPlayer && this.payoff[1][0].firstPlayer==this.payoff[1][1].firstPlayer )
            return true;

        return false;
    }

public boolean NanOrInf(double p){
        if (p==Double.POSITIVE_INFINITY||Double.isNaN(p))
            return true;
        return false;
}
    public void MixedNashEquilibria(){

        //       |   p    |     (1-p)
        // q     |
        // (1-q) |

        double q;
        q=(double) (this.payoff[1][1].secondPlayer-this.payoff[1][0].secondPlayer)/((this.payoff[0][0].secondPlayer-this.payoff[1][0].secondPlayer)-(this.payoff[0][1].secondPlayer-this.payoff[1][1].secondPlayer));
        double p;
        p= (double) (this.payoff[1][1].firstPlayer-this.payoff[0][1].firstPlayer)/((this.payoff[0][0].firstPlayer-this.payoff[0][1].firstPlayer)-(this.payoff[1][0].firstPlayer-this.payoff[1][1].firstPlayer));
        System.out.println("\n"+COLOR_E+"---------------------------------------------------------------------"+COLOR_RESET);
        if((isInfinityQ() || isInfinityP()) && (p<=1 || NanOrInf(p)) && (q<=1 || NanOrInf(q))){
            System.out.println(" There exist an infinite number of Nash equilibrium in mixed strategies");
        }


        if ((p>1 && !isInfinityP()) || (q>1 && !isInfinityQ())){
          System.out.println("There exist no Nash equilibrium in mixed strategies. ");
          return;
      }
       if(p>=0 && p<=1){
           System.out.println("p = "+toFraction(p));
       }else{
           System.out.println("p ∈ [0,1]");
       }
        if(q>=0 && q<=1){
           System.out.println("\nq = "+toFraction(q)+" \n");

        }else{
            System.out.println("q ∈ [0,1]");
        }


        System.out.println("\n"+COLOR_E+"---------------------------------------------------------------------"+COLOR_RESET);
    }
    public void PureNashEquilibria(){
        /// player 1
        for (int i=0;i<2;i++){
            if(this.payoff[0][i].firstPlayer>this.payoff[1][i].firstPlayer)
                this.payoff[0][i].isFirstplayerMax=true;
            else if (this.payoff[0][i].firstPlayer<this.payoff[1][i].firstPlayer)
                this.payoff[1][i].isFirstplayerMax=true;
            else
            {
                this.payoff[0][i].isFirstplayerMax=true;
                this.payoff[1][i].isFirstplayerMax=true;
            }
        }
        /// player 2
        for (int i=0;i<2;i++){
            if(this.payoff[i][0].secondPlayer>this.payoff[i][1].secondPlayer)
                this.payoff[i][0].isSecondplayerMax=true;
            else if (this.payoff[i][0].secondPlayer<this.payoff[i][1].secondPlayer)
                this.payoff[i][1].isSecondplayerMax=true;
            else
            {
                this.payoff[i][0].isSecondplayerMax=true;
                this.payoff[i][1].isSecondplayerMax=true;
            }
        }

    }


    public void Afficher() {
        System.out.println(" \nPayoff : *** player1/player2  ***\n ");
        System.out.println("\n"+COLOR_D+"---------------------------------------------------------------------"+COLOR_RESET);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
               if(this.payoff[i][j].isFirstplayerMax)
                    System.out.print("( "+COLOR_BLUE+this.payoff[i][j].firstPlayer+COLOR_RESET+',');
                else
                    System.out.print("( "+this.payoff[i][j].firstPlayer+',');
                ///*******************
                if(this.payoff[i][j].isSecondplayerMax)
                     System.out.print(COLOR_RED+this.payoff[i][j].secondPlayer+COLOR_RESET+')');
                 else
                    System.out.print(" "+this.payoff[i][j].secondPlayer+')');

                System.out.print("  ");

            }
            System.out.print("\n");
        }
    }


    public void afficher_menu(){
        System.out.println(COLOR_C);

        System.out.println("-----------------------Nash Equilibrium  ----------------------------");
        System.out.println("                   1.Pure Nash Equilibrium                                ");//ajouter un departement
        System.out.println("                   2.Mixed Nash Equilibrium                               ");
        System.out.println("                   3.Quitter                              ");
        System.out.println("Taper votre choix :                                       ");
        System.out.println(COLOR_RESET);

    }
}
