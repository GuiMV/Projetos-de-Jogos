'''
* Por: José Guilherme Moizinho Viana
* Disciplina: Algoritmo e Programação (Monitor)
* Projeto de disciplina - Campo Minado
'''

from CampoMinado import CampoMinado
from Interface import Interface

def ler_medida():
    while True:
        try:
            medida = int(input('Medida do campo[1-9]: '))

            if 1 <= medida <= 9:
                return medida

        except ValueError:
            pass
        
def ler_bombas(medida):
    while True:
        try:
            bombas = int(input(f'Nº de bombas ({medida}-{medida*medida}): '))

            if medida <= bombas <= medida * medida:
                return bombas

        except ValueError:
            pass

def main():
    print('\tCampo Minado')
    
    nome = input('Seu nome: ')
    medida = ler_medida()
    bombas = ler_bombas(medida)

    jogo = CampoMinado(medida, bombas)
    modo_dev = nome.lower() == "dev"

    while True:
        Interface.exibir(jogo, modo_dev)
        
        linha, coluna = Interface.ler_jogada(jogo.medida)
        resultado = jogo.jogar(linha, coluna)

        if resultado == "bomba":
            Interface.exibir(jogo, True)
            print(f'Boom! {nome} explodiu...')
            break

        if jogo.venceu():
            print(f'{nome}, você venceu!')
            break
    
if __name__ == '__main__':
    main()