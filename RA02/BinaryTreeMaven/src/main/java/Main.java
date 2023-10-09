public class Main {
    public static void main(String[] args) {
        BinaryTree arvore1 = new BinaryTree();
        BinaryTree arvore2 = new BinaryTree();
        BinaryTree arvore3 = new BinaryTree();
        BinaryTree arvore4 = new BinaryTree();
        BinaryTree arvore5 = new BinaryTree();

        performanceInsere(arvore1, 100);
        performanceInsere(arvore2, 500);
        performanceInsere(arvore3, 1000);
        performanceInsere(arvore4, 10000);
        performanceInsere(arvore5, 20000);

        Integer[] elementos1 = arvore1.toArray();
        arvore1.shuffleArray(elementos1);
        int quantidadeRandom1 = 50;
        arvore1.removeRandomNumbers(elementos1, quantidadeRandom1);

        Integer[] elementos2 = arvore2.toArray();
        arvore2.shuffleArray(elementos2);
        int quantidadeRandom2 = 250;
        arvore2.removeRandomNumbers(elementos2, quantidadeRandom2);

        Integer[] elementos3 = arvore3.toArray();
        arvore3.shuffleArray(elementos3);
        int quantidadeRandom3 = 500;
        arvore3.removeRandomNumbers(elementos3, quantidadeRandom3);

        Integer[] elementos4 = arvore4.toArray();
        arvore4.shuffleArray(elementos4);
        int quantidadeRandom4 = 5000;
        arvore4.removeRandomNumbers(elementos4, quantidadeRandom4);

        Integer[] elementos5 = arvore5.toArray();
        arvore5.shuffleArray(elementos5);
        int quantidadeRandom5 = 10000;
        arvore5.removeRandomNumbers(elementos5, quantidadeRandom5);


        // Testa se a arvore binaria e valida
        boolean valida = arvore1.validaArvoreBinaria();
        System.out.println(BinaryTree.validaMsg(valida));

        valida = arvore2.validaArvoreBinaria();
        System.out.println(BinaryTree.validaMsg(valida));

        valida = arvore3.validaArvoreBinaria();
        System.out.println(BinaryTree.validaMsg(valida));

        valida = arvore4.validaArvoreBinaria();
        System.out.println(BinaryTree.validaMsg(valida));

        valida = arvore5.validaArvoreBinaria();
        System.out.println(BinaryTree.validaMsg(valida));

        arvore1.imprimePreorder();
        arvore2.imprimePreorder();
        arvore3.imprimePreorder();
        arvore4.imprimePreorder();
        arvore5.imprimePreorder();

    }

    public static void performanceInsere(BinaryTree tree, int numeroElementos) {
        long startTime = System.nanoTime();
        tree.preenche(numeroElementos);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Tamanho arvore: " + numeroElementos);
        System.out.println("Tempo para preencher em nanosegundos: " + elapsedTime);
        System.out.println();
    }
}
