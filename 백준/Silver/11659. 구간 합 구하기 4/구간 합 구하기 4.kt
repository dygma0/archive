import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger

    var acc: Array<BigInteger>? = null

    fun main(args: Array<String>) {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        val read: () -> List<Int> = { br.readLine().trim().split(" ").map { x -> x.toInt() } }

        val (n, m) = read()
        val nums = read().toTypedArray()
        for (i in 0 until m) {
            val (s, e) = read()
            println(solution(nums, s, e))
        }

        bw.flush()
        bw.close()
        br.close()
    }

    fun solution(nums: Array<Int>, s: Int, e: Int): BigInteger {
        if (acc == null) {
            acc = Array(nums.size) { BigInteger.ZERO }.apply {
                this[0] = nums[0].toBigInteger()
                for (i in 1 until this.size) {
                    this[i] = this[i - 1] + nums[i].toBigInteger()
                }
            }
        }
        return acc!![e - 1] - acc!![s - 1] + nums[s - 1].toBigInteger()
    }
