function boards(){
var ret =
[









new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece(),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','O','R','G','B','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('O','P','R','Y','B','G'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece(),
      new Piece('Y','O','R','G','B','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('O','P','R','Y','B','G'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('R','O','P','G','Y','B'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('O','P','R','Y','B','G'),
      new Piece()
    ],
    [
      new Piece(),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('O','P','R','Y','B','G'),
      new Piece('G','Y','R','P','B','O')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('R','P','G','Y','O','B'),
      new Piece(),
      new Piece('G','Y','R','P','B','O')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('R','P','G','Y','O','B'),
      new Piece('O','B','P','R','Y','G'),
      new Piece('G','Y','R','P','B','O')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece(),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('R','P','G','Y','O','B'),
      new Piece('O','B','P','R','Y','G'),
      new Piece()
    ],
    [
      new Piece(),
      new Piece('B','Y','G','P','O','R'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('O','B','P','R','Y','G'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','B','G','R','O','Y'),
      new Piece(),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','B','G','R','O','Y'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece(),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','B','G','R','O','Y'),
      new Piece('Y','R','G','B','O','P'),
      new Piece()
    ],
    [
      new Piece(),
      new Piece('O','R','Y','B','P','G'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','B','G','R','O','Y'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('O','R','Y','B','P','G'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','B','G','R','O','Y'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece(),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','B','G','R','O','Y'),
      new Piece(),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','B','G','R','O','Y'),
      new Piece('R','O','P','G','Y','B'),
      new Piece()
    ],
    [
      new Piece(),
      new Piece('B','Y','G','P','O','R'),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('R','O','P','G','Y','B'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('R','P','G','Y','O','B'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','O','B','G','R','Y'),
      new Piece(),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('R','P','G','Y','O','B'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','O','B','G','R','Y'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('R','P','G','Y','O','B'),
      new Piece(),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('P','O','B','G','R','Y'),
      new Piece('Y','R','G','B','O','P'),
      new Piece()
    ],
    [
      new Piece(),
      new Piece('O','P','R','Y','B','G'),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('Y','R','G','B','O','P'),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('O','P','R','Y','B','G'),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','R','P','B','Y','O'),
      new Piece(),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('O','P','R','Y','B','G'),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','R','P','B','Y','O'),
      new Piece('B','P','O','Y','G','R'),
      new Piece()
    ],
    [
      new Piece(),
      new Piece('O','P','R','Y','B','G'),
      new Piece('P','R','O','B','G','Y')
    ]
  ]
)







];

return ret;
}