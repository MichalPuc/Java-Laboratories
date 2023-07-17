var initialize = function(board, rows, cols) {
    for (var i = 0; i < rows; i++) {
        board[i] = new Array(cols);
        for (var j = 0; j < cols; j++) {
            var randomValue = Math.floor(Math.random() * 4) + 1;
            board[i][j] = randomValue;
        }
    }




    return board;
};

var update = function(board, rows, cols) {
    var nextBoard = new Array(rows);
    for (var i = 0; i < rows; i++) {
        nextBoard[i] = new Array(cols);
        for (var j = 0; j < cols; j++) {
            var cell = board[i][j];
            switch (cell) {
                case 0: // Pusta przewodność
                    nextBoard[i][j] = 0;
                    break;
                case 1: // Przewodnik
                    if (hasNeighborElectronHead(board, rows, cols, i, j)) {
                        nextBoard[i][j] = 2; // Zmiana na Electron Head
                    } else {
                        nextBoard[i][j] = 1; // Bez zmian
                    }
                    break;
                case 2: // Electron Head
                    nextBoard[i][j] = 3; // Zmiana na Electron Tail
                    break;
                case 3: // Electron Tail
                    nextBoard[i][j] = 1; // Zmiana na Przewodnik
                    break;
            }
        }
    }

    for (var i = 0; i < rows; i++) {
        for (var j = 0; j < cols; j++) {
            board[i][j] = nextBoard[i][j];
        }
    }
};

var render = function(board, rows, cols) {
    for (var i = 0; i < rows; i++) {
        var rowString = "";
        for (var j = 0; j < cols; j++) {
            var cell = board[i][j];
            switch (cell) {
                case 0: // Pusta przewodność
                    rowString += ".";
                    break;
                case 1: // Przewodnik
                    rowString += "=";
                    break;
                case 2: // Electron Head
                    rowString += "H";
                    break;
                case 3: // Electron Tail
                    rowString += "T";
                    break;
            }
        }
        print(rowString);
    }
};

var unload = function(board) {
    board = [];
};

var hasNeighborElectronHead = function(board, rows, cols, row, col) {
    var directions = [
        [-1, -1], [-1, 0], [-1, 1],
        [0, -1],           [0, 1],
        [1, -1], [1, 0], [1, 1]
    ];

    for (var i = 0; i < directions.length; i++) {
        var direction = directions[i];
        var neighborRow = row + direction[0];
        var neighborCol = col + direction[1];

        if (
            neighborRow >= 0 &&
            neighborRow < rows &&
            neighborCol >= 0 &&
            neighborCol < cols
        ) {
            if (board[neighborRow][neighborCol] === 2) { // Sprawdzamy czy sąsiad to Electron Head
                return true;
            }
        }
    }
    return false;
};
