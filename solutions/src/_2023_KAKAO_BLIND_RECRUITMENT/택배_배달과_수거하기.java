package _2023_KAKAO_BLIND_RECRUITMENT;

public class 택배_배달과_수거하기 {
    public static void main(String[] args) {

    }
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        /*
        맨뒤부터 탐색
        배달 / 수거 중 먼저 발견 되는 곳을 저장 * 2 만큼 추가
            배달과 수거 각각 cap 만큼 저장
        배달과 수거해야하는 값을 만나면 저장된 값에서 빼기
        탐색을 이어나가면서 저장된 값이 0이 되면
            1. 동시에 0이 되면 탐색이어나감
            2. 0이 되었는데 남았으면 그 자리에서 거리 * 2 추가
         */
        long answer = 0;
        int delCap = 0;
        int pickCap = 0;
        for (int i = n - 1; i >= 0 ; i--) {
            if (deliveries[i] == 0 && pickups[i] == 0) {
                continue;
            }
            if (deliveries[i] != 0) {
                while (deliveries[i] > delCap) {
                    answer += (i + 1) * 2;
                    delCap += cap;
                    pickCap += cap;
                }
                delCap -= deliveries[i];
            }
            if (pickups[i] != 0) {
                while (pickups[i] > pickCap) {
                    answer += (i + 1) * 2;
                    delCap += cap;
                    pickCap += cap;
                }
                pickCap -= pickups[i];
            }
        }
        return answer;
    }
}
