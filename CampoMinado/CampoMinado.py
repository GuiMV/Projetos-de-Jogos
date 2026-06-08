'''
    Criando o objeto Campominado com as funções e atributos do jogo
'''
from random import sample

class CampoMinado:
    # DIRECOES É utilizada para verificar as posições adjacentes ao número
    DIRECOES = (
        (-1, -1), (-1, 0), (-1, 1),
        (0, -1),           (0, 1),
        (1, -1),  (1, 0),  (1, 1)
    )
    
    # Construtor: Medida do campo | Número de Bombas
    def __init__(self, medida, n_bombas):
        self.medida = medida
        self.n_bombas = n_bombas

        self.abertas = 0

        self.campo = [
            ['■'] * medida
            for _ in range(medida)
        ]

        self.minas = self._gerar_mapa()
        
    def _gerar_mapa(self):
        minas = [
            ['_'] * self.medida
            for _ in range(self.medida)
        ]

        bombas_linha = self.n_bombas // self.medida
        resto = self.n_bombas % self.medida

        # Distribui bombas
        for l in range(self.medida):
            quantidade = bombas_linha

            if resto > 0:
                quantidade += 1
                resto -= 1

            posicoes = sample(
                range(self.medida),
                quantidade
            )

            for c in posicoes:
                minas[l][c] = '•'

        # Calcula números
        for l in range(self.medida):
            for c in range(self.medida):
                if minas[l][c] == '•':
                    continue

                bombas = 0

                for dy, dx in self.DIRECOES:
                    nl = l + dy
                    nc = c + dx

                    if (
                        0 <= nl < self.medida
                        and
                        0 <= nc < self.medida
                    ):
                        if minas[nl][nc] == '•':
                            bombas += 1

                minas[l][c] = str(bombas)

        return minas

    def abrir(self, linha, coluna):
        if self.campo[linha][coluna] == '■':
            self.campo[linha][coluna] = self.minas[linha][coluna]
            self.abertas += 1
        
    def expandir_zeros(self, linha, coluna):
        '''
            Função recursiva que expande as células vazias(0) do campo
            l (int): Linha da célula.
            c (int): Coluna da célula.
        '''
        
        self.abrir(linha, coluna)
        mudou = True

        while mudou:
            mudou = False
            for l in range(self.medida):
                for c in range(self.medida):
                    
                    if self.campo[l][c] == '0':
                        
                        for dy, dx in self.DIRECOES:
                            nl = l + dy
                            nc = c + dx

                            if (
                                0 <= nl < self.medida
                                and
                                0 <= nc < self.medida
                            ):

                                if self.campo[nl][nc] == '■':
                                    self.abrir(
                                        nl,
                                        nc
                                    )

                                    if self.minas[nl][nc] == '0':
                                        mudou = True

#     def perdeu(self, linha, coluna):
#         return self.minas[linha][coluna] == '•'
    
    def venceu(self):
        return self.abertas == self.medida * self.medida - self.n_bombas

    def jogar(self, linha, coluna):

        if self.campo[linha][coluna] != '■':
            return "aberta"

        valor = self.minas[linha][coluna]

        if valor == '•':
            self.abrir(
                linha,
                coluna
            )

            return "bomba"

        if valor == '0':
            self.expandir_zeros(
                linha,
                coluna
            )
            return "zero"

        self.abrir(
            linha,
            coluna
        )

        return "numero"