package programming.algorithm.euclid;
/*
 * Euclid Algorithm
 * Calculates the greatest common divisor between two numbers A and B
 *
 **/
public class Euclid {
    /*
     * Implementation is using recursion
     * 22/6 = 3 rest of 4
     * 6/4 = 1 rest of 2
     * 4/2 = 2 rest of 0
     * */
    public int RecursiveGcd(int number, int divisor) {
        int remaining = (number % divisor);

        if (remaining != 0) {
            return RecursiveGcd(divisor, remaining);
        } else{
            return divisor;
        }
    }
    //implementation without recursion
      /*
       * 22/6 = 3 rest of 4
       * 6/4 = 1 rest of 2
       * 4/2 = 2 rest of 0
       *
       * number/ temp = result rest of divisor
        **/
    public int IterativeGcd(int number, int divisor){
        //This needs a loop
        while(divisor != 0){
            int tmp = divisor;
            divisor = (number  % divisor);
            number = tmp;
        }
        return number;
    }
}
