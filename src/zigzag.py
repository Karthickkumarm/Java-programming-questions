def printZigZag(matrix):
    rows = len(matrix)
    cols = len(matrix[0])

    # Result list to store the zig-zag traversal
    result = []

    # Traverse diagonals
    for d in range(rows + cols - 1):
        if d % 2 == 0:
            # Traverse in the upward direction
            r = min(d, rows - 1)
            c = d - r
            while r >= 0 and c < cols:
                result.append(matrix[r][c])
                r -= 1
                c += 1
        else:
            # Traverse in the downward direction
            c = min(d, cols - 1)
            r = d - c
            while c >= 0 and r < rows:
                result.append(matrix[r][c])
                r += 1
                c -= 1

    # Print the result
    print(" ".join(map(str, result)))

# Example usage
matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
]

printZigZag(matrix)
