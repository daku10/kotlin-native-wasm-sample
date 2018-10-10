const updateBoard = (oldBoard, newBoard, width, height) => {
  for (let y = 1; y <= height; y++) {
    for (let x = 1; x <= width; x++) {
      const count = countAroundLiveCells(oldBoard, x, y);
      if (oldBoard[x][y] === 0) {
        newBoard[x][y] = (count === 3) ? 1 : 0;
      } else {
        newBoard[x][y] = (count === 2 || count === 3) ? 1 : 0;
      }
    }
  }
}

const countAroundLiveCells = (board, x, y) => {
  return board[x-1][y-1]
       + board[x-1][y]
       + board[x-1][y+1]
       + board[x][y-1]
       + board[x][y+1]
       + board[x+1][y-1]
       + board[x+1][y]
       + board[x+1][y+1]
}

const canvas = document.getElementById('myCanvas');
const ctx = canvas.getContext('2d');
const cellSize = 1;
const canvasWidth = 640;
const canvasHeight = 640;
const width = canvasWidth / cellSize;
const height = canvasHeight / cellSize;
const deadStyle = 'rgb(60,60,60)';
const liveStyle = 'rgb(200,100,0)';

let generation = 0;
const boards = new Array();
boards[0] = new Array();
boards[1] = new Array();
for (let x = 0; x <= width + 1; x++) {
  boards[0][x] = new Array();
  boards[1][x] = new Array();
}

const getRandomInt = (max) => {
  return Math.floor(Math.random() * Math.floor(max));
}

const drawBoard = (board) => {
  for (let y = 1; y <= height; y++) {
    for (let x = 1; x <= width; x++) {
      const style = (board[x][y] == 1) ? liveStyle : deadStyle;
      ctx.fillStyle = style;
      ctx.fillRect((x - 1) * cellSize, (y - 1) * cellSize, cellSize, cellSize);
    }
  }
}

for (let i = 0; i < 2; i++) {
  const board = boards[i];
  for (let x = 0; x <= width + 1; x++) {
    board[x][0] = 0;
    board[x][height + 1] = 0;
  }
  for (let y = 1; y <= height; y++) {
    board[0][y] = 0;
    board[width + 1][y] = 0;
  }

  for (let y = 1; y <= height; y++) {
    for (let x = 1; x <= width; x++) {
      board[x][y] = getRandomInt(2);
    }
  }
}

drawBoard(boards[0]);

setInterval(() => {
  const updateBefore = Date.now()
  updateBoard(boards[generation%2], boards[(generation+1)%2], width, height)
  const drawBefore = Date.now()
  const updateEnd = drawBefore
  drawBoard(boards[generation%2])
  const drawEnd = Date.now()
  generation++
  console.log("更新:" + (updateEnd - updateBefore))
  console.log("描画:" + (drawEnd - drawBefore))
  console.log("更新+描画:" + (drawEnd - updateBefore))
}, 10);
