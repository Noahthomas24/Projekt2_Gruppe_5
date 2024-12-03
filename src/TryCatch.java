public class TryCatch {

    public static int indtastTal(){
        while (true){
            String input = Medlem.scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("Ugyldigt valg, prøv igen");
            }
        }
    }
}
