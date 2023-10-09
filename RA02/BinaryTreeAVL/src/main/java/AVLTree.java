import java.util.Random;
public class AVLTree {

    Node raiz;

    int altura(Node N) {
        if (N == null)
            return 0;

        return N.altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rotacionaDireita(Node y) {
        Node x = y.esquerda;
        Node T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    Node rotacionaEsquerda(Node x) {
        Node y = x.direita;
        Node T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    int Balanceamento(Node atual) {
        if (atual == null)
            return 0;

        return altura(atual.esquerda) - altura(atual.direita);
    }

    public Node insert(Node node, int chave) {

        if (node == null)
            return (new Node(chave));

        if (chave < node.chave)
            node.esquerda = insert(node.esquerda, chave);
        else if (chave > node.chave)
            node.direita = insert(node.direita, chave);
        else
            return node;

        node.altura = 1 + max(altura(node.esquerda),
                altura(node.direita));

        //DEFINE A ACAO DE ACORDO COM O BALANCEAMENTO
        int balance = Balanceamento(node);

        if (balance > 1 && chave < node.esquerda.chave)
            return rotacionaDireita(node);

        if (balance < -1 && chave > node.direita.chave)
            return rotacionaEsquerda(node);

        if (balance > 1 && chave > node.esquerda.chave) {
            node.esquerda = rotacionaEsquerda(node.esquerda);
            return rotacionaDireita(node);
        }

        if (balance < -1 && chave < node.direita.chave) {
            node.direita = rotacionaDireita(node.direita);
            return rotacionaEsquerda(node);
        }

        return node;
    }

    public Node nodeMenorValor(Node node) {
        Node atual = node;

        while (atual.esquerda != null)
            atual = atual.esquerda;

        return atual;
    }

    public Node removeRec(Node raiz, int chave) {
        if (raiz == null)
            return raiz;

        if (chave < raiz.chave) {
            raiz.esquerda = removeRec(raiz.esquerda, chave);
        }
        else if (chave > raiz.chave) {
            raiz.direita = removeRec(raiz.direita, chave);
        }
        else {

            if ((raiz.esquerda == null) || (raiz.direita == null))
            {
                Node temp = (raiz.esquerda != null) ? raiz.esquerda : raiz.direita;

                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                }
                else
                    raiz = temp;

            } else {
                Node temp = nodeMenorValor(raiz.direita);

                raiz.chave = temp.chave;

                raiz.direita = removeRec(raiz.direita, temp.chave);
            }
        }

        if (raiz == null)
            return raiz;

        raiz.altura = max(altura(raiz.esquerda), altura(raiz.direita)) + 1;

        int balance = Balanceamento(raiz);

        if (balance > 1 && Balanceamento(raiz.esquerda) >= 0)
            return rotacionaDireita(raiz);

        if (balance > 1 && Balanceamento(raiz.esquerda) < 0)
        {
            raiz.esquerda = rotacionaEsquerda(raiz.esquerda);
            return rotacionaDireita(raiz);
        }

        if (balance < -1 && Balanceamento(raiz.direita) <= 0)
            return rotacionaEsquerda(raiz);

        if (balance < -1 && Balanceamento(raiz.direita) > 0)
        {
            raiz.direita = rotacionaDireita(raiz.direita);
            return rotacionaEsquerda(raiz);
        }

        return raiz;
    }


    public void preenche(int numeroElementos) {
        long seed = 4913;
        Random randomNum = new Random(seed);

        for (int i = 0; i < numeroElementos; i++) {
            int random = randomNum.nextInt(100000);
            raiz = insert(raiz, random);
            //System.out.println(random);  //Imprime valores se necesario
        }
    }

    public boolean validaAVL(Node atual) {
        if (atual == null) {
            return true;
        }

        int balance = Balanceamento(atual);

        if (Math.abs(balance) > 1) {
            return false;
        }

        return validaAVL(atual.esquerda) && validaAVL(atual.direita);
    }

    public boolean estaBalanceada(Node atual, Integer anterior) {
        if (atual == null) {
            return true;
        }

        if (!estaBalanceada(atual.esquerda, anterior)) {
            return false;
        }

        if (anterior != null && atual.chave <= anterior) {
            return false;
        }

        anterior = atual.chave;

        return estaBalanceada(atual.direita, anterior);
    }

    public void busca(Node raiz, int quantidadeRandom) {
        long seed = 4913;
        Random randomNumero = new Random(seed);

        for (int i = 0; i < quantidadeRandom; i++) {
            int random = randomNumero.nextInt(100000);
            Node result = search(raiz, random);

            if (result != null) {
                System.out.println("Valor " + random + " encontrado");
            } else {
                System.out.println("Valor " + random + " nao encontrado");
            }
        }
    }

    public Node search(Node raiz, int chave) {
        if (raiz == null || raiz.chave == chave) {
            return raiz;
        }

        if (chave < raiz.chave) {
            return search(raiz.esquerda, chave);
        } else {
            return search(raiz.direita, chave);
        }
    }

    public void removeRandomNumbers(Node raiz, int[] arrayEmbaralhado, int quantidadeRandom) {
        long startTime = System.nanoTime();

        for (int i = 0; i < quantidadeRandom; i++) {
            int number = arrayEmbaralhado[i];
            Node resultado = search(raiz, number);

            if (resultado != null) {
                // System.out.println("Valor " + number + " encontrado e removido");
                raiz = removeRec(raiz, number);
            } /*
            else {
                System.out.println("Valor " + number + " nao encontrado");
            }
            */
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Tempo para remover " + quantidadeRandom + " elementos em nanosegundos: " + elapsedTime);
    }

    public void imprimePreorder(Node raiz) {
        if (raiz == null) {
            return;
        }

        System.out.print(raiz.chave + " ");

        imprimePreorder(raiz.esquerda);
        imprimePreorder(raiz.direita);
    }

}
