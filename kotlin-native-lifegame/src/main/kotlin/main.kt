import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*
import kotlin.random.*
import kotlin.system.*

fun updateBoard(oldBoard: Array<Array<Int>>, newBoard: Array<Array<Int>>, width: Int, height: Int) {
  for (y in 1..height) {
    for (x in 1..width) {
      val count = countAroundLiveCells(oldBoard, x, y)
      if (oldBoard[x][y] == 0) {
        if (count == 3) {
          newBoard[x][y] = 1
        } else {
          newBoard[x][y] = 0
        }
      } else {
        if (count == 2 || count == 3) {
          newBoard[x][y] = 1
        } else {
          newBoard[x][y] = 0
        }
      }
    }
  }
}

fun countAroundLiveCells(board: Array<Array<Int>>, x: Int, y: Int) : Int {
  return board[x-1][y-1] +
         board[x-1][y] +
         board[x-1][y+1] +
         board[x][y-1] +
         board[x][y+1] +
         board[x+1][y-1] +
         board[x+1][y] +
         board[x+1][y+1]
}

fun main(args: Array<String>) {
    val canvas = document.getElementById("myCanvas").asCanvas
    val ctx = canvas.getContext("2d")
    val cellSize = 1;
    val canvasWidth = 640; // 本当はCanvasからサイズを取りたい
    val canvasHeight = 640;
    val width = canvasWidth / cellSize;
    val height = canvasHeight / cellSize;
    val deadStyle = "rgb(60,60,60)";
    val liveStyle = "rgb(200,100,0)";

    var generation = 0;
    // 境界値を考えないために0 ~ width + 1 まで用意して、ボードとしては 1 ~ width を使う
    val boards : Array<Array<Array<Int>>> = Array(2, { _ -> Array(width + 2, { _ -> Array(height + 2, { _ -> 0 }) }) });

    for (y in 1..height) {
      for (x in 1..width) {
        boards[0][x][y] = Random.nextInt(2);
      }
    }

    val drawBoard = { board: Array<Array<Int>> ->
      for (y in 1..height) {
        for (x in 1..width) {
          val style = if (board[x][y] == 1) liveStyle else deadStyle;
          ctx.fillStyle = style;
          ctx.fillRect((x - 1) * cellSize, (y - 1) * cellSize, cellSize, cellSize);
        }
      }
    }

    drawBoard(boards[0]);

    setInterval(10) {
      val updateBefore = getTimeMillis()
      updateBoard(boards[generation%2], boards[(generation+1)%2], width, height)
      generation++
      val updateEnd = getTimeMillis()
      val drawBefore = updateEnd
      drawBoard(boards[generation%2])
      val drawEnd = getTimeMillis()
      println("更新処理" + (updateEnd - updateBefore))
      println("描画処理" + (drawEnd - drawBefore))
      println("更新+描画" + (drawEnd - updateBefore))
    }
}
