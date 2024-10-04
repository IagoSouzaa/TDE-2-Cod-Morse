import java.util.Scanner;

class TreeNode {
    char value;
    TreeNode left;
    TreeNode right;

    public TreeNode(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class MorseBinarySearchTree {
    private TreeNode root;

    public MorseBinarySearchTree() {
        root = null;
    }

    public void insertBST(char value) {
        root = insertBSTRecursive(root, value);
    }

    private TreeNode insertBSTRecursive(TreeNode node, char value) {
        if (node == null) {
            return new TreeNode(value);
        }

        if (value < node.value) {
            node.left = insertBSTRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertBSTRecursive(node.right, value);
        }

        return node;
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

    private void printTreeRecursive(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        printTreeRecursive(node.right, level + 1);

        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|---" + node.value);
        } else {
            System.out.println(node.value);
        }

        printTreeRecursive(node.left, level + 1);
    }

    public char decodeMorse(String morseCode) {
        String[] morseAlphabet = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
                "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        for (int i = 0; i < morseAlphabet.length; i++) {
            if (morseCode.equals(morseAlphabet[i])) {
                return letters[i];
            }
        }
        return '?';
    }

    public static void main(String[] args) {
        MorseBinarySearchTree tree = new MorseBinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o código morse (separe as letras com espaço):");
        String morseInput = scanner.nextLine();
        String[] morseLetters = morseInput.split(" ");
        StringBuilder decodedWord = new StringBuilder();

        for (String morseLetter : morseLetters) {
            char decodedChar = tree.decodeMorse(morseLetter);
            decodedWord.append(decodedChar);

            if (decodedChar != '?') {
                tree.insertBST(decodedChar);
            }
        }

        System.out.println("Palavra decodificada: " + decodedWord.toString());

        System.out.println("Árvore binária de busca desenhada com os caracteres decodificados:");
        tree.printTree();

        scanner.close();
    }
}
