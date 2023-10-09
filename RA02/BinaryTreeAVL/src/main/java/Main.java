import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AVLTree arvore1 = new AVLTree();
        AVLTree arvore2 = new AVLTree();
        AVLTree arvore3 = new AVLTree();
        AVLTree arvore4 = new AVLTree();
        AVLTree arvore5 = new AVLTree();

        performanceInsere(arvore1, 100);
        performanceInsere(arvore2, 500);
        performanceInsere(arvore3, 1000);
        performanceInsere(arvore4, 10000);
        performanceInsere(arvore5, 20000);

        int[] numerosArvore1 = new int[100];
        int[] numerosArvore2 = new int[500];
        int[] numerosArvore3 = new int[1000];
        int[] numerosArvore4 = new int[10000];
        int[] numerosArvore5 = new int[20000];

        int[] index = new int[1];

        gatherNumbersInOrder(arvore1.raiz, numerosArvore1, index);
        shuffleArray(numerosArvore1);
        arvore1.removeRandomNumbers(arvore1.raiz, numerosArvore1, 50);

        index[0] = 0; // Reset the index for the next tree

        gatherNumbersInOrder(arvore2.raiz, numerosArvore2, index);
        shuffleArray(numerosArvore2);
        arvore2.removeRandomNumbers(arvore2.raiz, numerosArvore2, 250);

        index[0] = 0;

        gatherNumbersInOrder(arvore3.raiz, numerosArvore3, index);
        shuffleArray(numerosArvore3);
        arvore3.removeRandomNumbers(arvore3.raiz, numerosArvore3, 500);

        index[0] = 0;

        gatherNumbersInOrder(arvore4.raiz, numerosArvore4, index);
        shuffleArray(numerosArvore4);
        arvore4.removeRandomNumbers(arvore4.raiz, numerosArvore4, 5000);

        index[0] = 0;

        gatherNumbersInOrder(arvore5.raiz, numerosArvore5, index);
        shuffleArray(numerosArvore5);
        arvore5.removeRandomNumbers(arvore5.raiz, numerosArvore5, 10000);


        // ve se as arvores sao validas e balanceadas
        System.out.println("Arvore1: " + arvore1.validaAVL(arvore1.raiz));
        System.out.println("Is Sorted: " + arvore1.estaBalanceada(arvore1.raiz, null));

        System.out.println("Arvore2: " + arvore2.validaAVL(arvore2.raiz));
        System.out.println("Is Sorted: " + arvore2.estaBalanceada(arvore2.raiz, null));

        System.out.println("Arvore3: " + arvore3.validaAVL(arvore3.raiz));
        System.out.println("Is Sorted: " + arvore3.estaBalanceada(arvore3.raiz, null));

        System.out.println("Arvore4: " + arvore4.validaAVL(arvore4.raiz));
        System.out.println("Is Sorted: " + arvore4.estaBalanceada(arvore4.raiz, null));

        System.out.println("Arvore5: " + arvore5.validaAVL(arvore5.raiz));
        System.out.println("Is Sorted: " + arvore5.estaBalanceada(arvore5.raiz, null));

        arvore1.imprimePreorder(arvore1.raiz);
        System.out.println(" ");
        arvore2.imprimePreorder(arvore2.raiz);
        System.out.println(" ");
        arvore3.imprimePreorder(arvore3.raiz);
        System.out.println(" ");
        arvore4.imprimePreorder(arvore4.raiz);
        System.out.println(" ");
        arvore5.imprimePreorder(arvore5.raiz);
        System.out.println(" ");

    }

    public static void performanceInsere(AVLTree tree, int numeroElementos) {
        long startTime = System.nanoTime();
        tree.preenche(numeroElementos);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Tamanho arvore: " + numeroElementos);
        System.out.println("Tempo para preencher em nanossegundos: " + elapsedTime);
        System.out.println();
    }

    public static void gatherNumbersInOrder(Node root, int[] result, int[] index) {
        if (root == null) {
            return;
        }

        gatherNumbersInOrder(root.esquerda, result, index);
        result[index[0]++] = root.chave;
        gatherNumbersInOrder(root.direita, result, index);
    }

    public static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
