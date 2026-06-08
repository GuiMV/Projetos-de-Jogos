class Interface:
    @staticmethod
    def exibir(jogo, modo_dev=False):
        colunas = ' '.join(
            str(i)
            for i in range(1, jogo.medida + 1)
        )

        print('#', colunas)

        for i in range(jogo.medida):
            if modo_dev:
                print(
                    i + 1,
                    ' '.join(jogo.campo[i]),
                    ' '.join(jogo.minas[i])
                )
            else:
                print(
                    i + 1,
                    ' '.join(jogo.campo[i])
                )

    @staticmethod
    def ler_jogada(medida):
        while True:
            try:
                l, c = map(
                    int,
                    input('L C: ').split()
                )

                if 1 <= l <= medida and 1 <= c <= medida:
                    return l - 1, c - 1

            except ValueError:
                pass

            print('Posição inválida.')