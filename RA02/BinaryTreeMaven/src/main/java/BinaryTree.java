// import java.util.Objects;
import java.util.Random;

public class BinaryTree {
    Node raiz;

    public void insere(Integer chave, Integer valor) {
        raiz = insereRec(raiz, chave, valor);
    }

    private Node insereRec(Node atual, Integer chave, Integer valor) {
        if(atual == null) return new Node(chave, valor, 1);

        int direcao = comparaChaves(chave, atual.chave);

        if(direcao < 0 ) {
            atual.esquerda = insereRec(atual.esquerda, chave, valor);
        }
        else if (direcao > 0) {
            atual.direita = insereRec(atual.direita, chave, valor);
        }
        else atual.valor = valor;

        // ATUALIZA O TAMANHO DA SUBTREE
        atual.tamanho = tamanhoNode(atual.esquerda) + tamanhoNode(atual.direita) + 1;

        return atual;
    }

    private Node removeRec(Node atual, Integer chave) {
        if (atual == null) {
            return atual;
        }

        int direcao = comparaChaves(chave, atual.chave);

        if (direcao < 0) {
            atual.esquerda = removeRec(atual.esquerda, chave);
        } else if (direcao > 0) {
            atual.direita = removeRec(atual.direita, chave);
        } else {
            // Node with one or no child
            if (atual.esquerda == null) {
                return atual.direita;
            } else if (atual.direita == null) {
                return atual.esquerda;
            }

            atual.chave = minValue(atual.direita);
            atual.direita = removeRec(atual.direita, atual.chave);
        }

        atual.tamanho = tamanhoNode(atual.esquerda) + tamanhoNode(atual.direita) + 1;

        return atual;
    }

    private Integer minValue(Node node) {
        Integer minValue = node.chave;
        while (node.esquerda != null) {
            minValue = node.esquerda.chave;
            node = node.esquerda;
        }
        return minValue;
    }

    public boolean validaArvoreBinaria() {
        return validaArvore(raiz, null, null);
    }

    private boolean validaArvore(Node atual, Integer min, Integer max) {
        if(atual == null) return true;

        if((min == null || atual.chave > min) && (max == null || atual.chave < max)) {
            if(atual.esquerda != null && (atual.esquerda.chave >= atual.chave)) {
                return false;
            }
            if(atual.direita != null && (atual.direita.chave <= atual.chave)) {
                return false;
            }
            return validaArvore(atual.esquerda, min, atual.chave) && validaArvore(atual.direita, atual.chave, max);
        }
        return false;
    }

    private int comparaChaves(Integer chave, Integer chaveAtual) {
        if(chave < chaveAtual) {
            return -1;
        } else if (chave > chaveAtual) {
            return 1;
        } else {
            return 0;
        }
    }

    public void preenche(int numeroElementos) {
        long seed = 4913;
        Random randomNum = new Random(seed);

        for (int i = 0; i < numeroElementos + 1; i++) {
            int random = randomNum.nextInt(100000);
            insere(random, i);
            //System.out.println(random); //Imprime os numeros a serem inseridos na arvore
        }
    }

    public Node busca(Integer chave) {
        return searchRec(raiz, chave);
    }

    private Node searchRec(Node node, Integer chave) {
        if (node == null || chave.equals(node.chave)) {
            return node;
        }

        if (chave < node.chave) {
            return searchRec(node.esquerda, chave);
        } else {
            return searchRec(node.direita, chave);
        }
    }


    public int tamanhoNode() {
        return tamanhoNode(raiz);
    }


    private int tamanhoNode(Node node) {
        if (node == null) {
            return 0;
        }
        return node.tamanho;
    }

    public static String validaMsg(boolean isValid) {
        if (isValid) {
            return "A arvore binaria e valida";
        } else {
            return "A arvore binaria nao e valida";
        }
    }

    public Integer[] toArray() {
        Integer[] resultado = new Integer[tamanhoNode()];
        toArrayInOrder(raiz, resultado, 0);
        return resultado;
    }

    private int toArrayInOrder(Node node, Integer[] result, int index) {
        if (node == null) {
            return index;
        }

        index = toArrayInOrder(node.esquerda, result, index);
        result[index++] = node.chave;
        index = toArrayInOrder(node.direita, result, index);

        return index;
    }

    public void shuffleArray(Integer[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            Integer temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public void removeRandomNumbers(Integer[] shuffledNumbers, int quantidadeRandom) {
        long startTime = System.nanoTime();

        for (int i = 0; i < quantidadeRandom; i++) {
            Integer numero = shuffledNumbers[i];
            Node resultado = busca(numero);

            if (resultado != null) {
                raiz = removeRec(raiz, numero);
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Tempo para remover " + quantidadeRandom + " elementos em nanosegundos: " + elapsedTime);
    }

    public void imprimePreorder() {
        printPreorderRec(raiz);
        System.out.println();
    }

    private void printPreorderRec(Node node) {
        if (node != null) {
            System.out.print(node.chave + " ");
            printPreorderRec(node.esquerda);
            printPreorderRec(node.direita);
        }
    }
}
