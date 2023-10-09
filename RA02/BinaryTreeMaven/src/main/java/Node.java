public class Node {
    Integer chave, valor;
    int tamanho;
    Node esquerda, direita;

    public Node(int chave, int valor, int tamanho) {
        this.chave = chave;
        this.valor = valor;
        this.tamanho = tamanho;
    }
}