import sys


def solution(k: int, coins: [int]) -> int:
    answer = 0
    remain_coin = k
    for coin in reversed(coins):
        answer += remain_coin // coin
        remain_coin %= coin
    return answer


n, k = map(int, sys.stdin.readline().split())
coins = []
for _ in range(n):
    coins.append(int(sys.stdin.readline()))

print(solution(k, coins))
