def novoJogo():
    ''' Inicia o Jogo definindo as marcas dos jogadores '''
    print("\tJogo da Velha\n")
    
    while True:
        m1 = input("Marca do P1 >> ").strip().upper()
        if len(m1) == 1: break
    while True:
        m2 = input("Marca do P2 >> ").strip().upper()
        if m2 != m1 and len(m2) == 1: break
    
    return m1, m2
    
def lob(m1, m2):
    ''' Exibe o Placar com os simpobolos e pontuação dos jogadores '''
    print(f'\n\tPlayer {m1}\t|\tPlayer {m2}\n\t   {pontos1}\t\t\t   {pontos2}\n')

def exibe(velha):
    ''' Exibe a Velha '''
    print( '   A   B   C')
    for i in range(3):
        print(f'{i+1}  {velha[i][0]} | {velha[i][1]} | {velha[i][2]}')
        if i < 2:
            print('  ---+---+---')
      
def marca(jogador, velha):
    coluna = {'A': 1, 'B': 2, 'C': 3}
    marcou = False
    
    while True:
        try:
            L, C = list(input('>> ').upper()) #1C ou C1
            if L.isalpha() and C.isnumeric() and L in coluna:
                L, C = int(C), coluna[L]
                marcou = True
                
            elif L.isnumeric() and C.isalpha() and C in coluna:
                L, C = int(L), coluna[C]
                marcou = True
                
            if marcou and 0 < L < 4 and 0 < C < 4 and velha[L-1][C-1] == ' ':
                velha[L-1][C-1] = jogador
                return ganhou(jogador, velha, L-1, C-1)
            
        except ValueError:
            continue

def ganhou(marca, velha , l, c):
    ''' Analisa as linhas, colunas e diagonais da Velha '''
    
    # Linhas
    if all(velha[l][i] == marca for i in range(3)):
        return True
        
    # Coluna
    if all(velha[i][c] == marca for i in range(3)):
        return True
        
    # Diagonal principal
    if l == c:
        if all(velha[i][i] == marca  for i in range(3)):
            return True
    
    # Diagonal secundária
    if l + c == 2:
        if all(velha[i][2-i] == marca for i in range(3)):
            return True
    
    return False
        
def loop(P1, P2):
    global pontos1, pontos2
    velha = [[' ' for _ in range(3)] for _ in range (3)]
    controle, livres = 0, 9
    
    while True:
        jogador = (P1, P2)[controle]
        controle = 0 if controle else 1
        
        exibe(velha)
        vitoria = marca(jogador, velha)
        livres -= 1
        
        if vitoria:
            exibe(velha)
            print(f'Vitória do {jogador}!')
            
            if controle: pontos1 += 1
            else: pontos2 += 1
            
            return
        
        if livres == 0:
            exibe(velha)
            print(f'Deu Velha!')
            return
        
pontos1, pontos2 = 0, 0
while True:
    marca1, marca2 = novoJogo()
    lob(marca1, marca2)
    loop(marca1, marca2)
    
    lob(marca1, marca2)
    
    if input('Enter para continuar') != '':
        break