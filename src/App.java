import java.util.*;


public class App {
    private List<Integer> randomArr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    private int opt_size = 3; //기본 사이즈
    private int count=0;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while(true) {
            System.out.println("환영합니다! 원하시는 번호를 입력해주세요\n" +
                    "1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
            String input = scanner.nextLine();

            if(!isValidNumber(input)){
                System.out.println("올바르지 않은 입력값입니다.");
                continue;
            }

            if(input.equals("1")){
                game();
            }else if(input.equals("2")){
                continue;
            }else if(input.equals("3")) {
                break;
            }else{
                System.out.println("올바르지 않는 입력값입니다.");
            }
        }
        scanner.close();
    }
    private void game(){
        String answer = makeAnswer();
        System.out.println(answer);
        while (true) {
            System.out.println("숫자를 입력하세요");

            // 1. 유저에게 입력값을 받음
            String input = scanner.nextLine();

            // 2. 정수로 변환되지 않는 경우 반복문 처음으로 돌아가기
            if(!isValidNumber(input)){
                System.out.println("올바르지 않은 입력값입니다.");
                continue;
            }

            // 3. 세 자리가 아니거나, 0을 가지거나 특정 숫자가 두 번 사용된 경우 반복문 처음으로 돌아가기
            if (!isThreeDigitNumber(input) || hasDuplicateDigits(input) || containsZero(input)) {
                System.out.println("올바르지 않은 입력값입니다.");
                continue;
            }

            // 4. 정답과 유저의 입력값을 비교하여 스트라이크/볼을 출력하기
            // 만약 정답이라면 break 호출하여 반복문 탈출
            if(input.equals(answer)){
                System.out.println("정답입니다.");
                break;
            }else{
                System.out.println(getStrikeAndBall(answer,input));
                count++;
            }
        }
    }

    private String makeAnswer() {
        // 정답을 만드는 로직을 구현하세요.
        Collections.shuffle(randomArr);
        StringBuffer answer = new StringBuffer();

        for(int i=0; i<opt_size; i++) {
            answer.append(randomArr.get(i));
        }

        return answer.toString(); // 예제용 정답
    }

    private boolean isValidNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isThreeDigitNumber(String input) {
        if (input.length() != opt_size) {
            return false;
        }else{
            return true;
        }
    }

    private boolean containsZero(String input) {
        if(input.contains("0")){
            return true;
        }else {
            return false;
        }
    }

    private boolean hasDuplicateDigits(String input) {
        Set<Character> temp = new HashSet<>();
        for(char word : input.toCharArray()) {
            if(!temp.add(word)){
                return true;
            }
        }
        return false;
    }

    private String getStrikeAndBall(String answer, String input) {
        int strike = 0;
        int ball = 0;

        for(int i=0; i<answer.length(); i++) {
            if(answer.charAt(i) == input.charAt(i)) {
                strike++;
            }else if(answer.contains(String.valueOf(input.charAt(i)))) {
                ball++;
            }
        }

        if(strike > 0 || ball > 0){
            return strike + "스트라이크 " + ball + "볼";
        }else{
            return "아웃";
        }
    }
}
