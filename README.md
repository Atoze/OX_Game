# OX_Game　[![CircleCI](https://circleci.com/gh/Atoze/OX_Game.svg?style=svg)](https://circleci.com/gh/Atoze/OX_Game)

## Description
簡易的な ~~○×ゲーム~~ 五目並べです.

## Requirement
Java 1.8.0

## HowToPlay
1. ./gradlew runで実行できます.
2. 先手プレイヤーはCPUです.初手は必ずボードの中心に置かれます.
3. 後攻プレイヤーはあなたです.あなたの初手は、必ず中心(相手の初手)に置かれたマスに隣接する場所でないといけません.
4. それ以降は自由におけます.
CPUと交互に数字を入力し、相手より先に自分のマスが5個並ぶ状態を作ることができれば勝利です.

なお、このゲームでは次の手を置くまでに時間制限が設けられています.
時間制限内に置くことが出来なければ、ランダムで自分の手が決定されます.

デフォルトの時間制限は20秒です.

## Author
[Atoze](https://github.com/Atoze)
