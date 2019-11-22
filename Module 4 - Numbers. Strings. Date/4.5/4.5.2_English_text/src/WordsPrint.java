public class WordsPrint {
    public static void main(String[] args) {
       textPrint();
    }

    public static void textPrint() {
        String text = "Well, then, said he, this is the berth for me. Here you, matey, he cried to the man who trundled the barrow; bring up alongside and help up my chest. Ill stay here a bit, he continued. Im a plain man; rum and bacon and eggs is what I want, and that head up there for to watch ships off. What you mought call me? You mought call me captain. Oh, I see what youre at there; and he threw down three or four gold pieces on the threshold. You can tell me when Ive worked through that, says he, looking as fierce as a commander";

        String[] words = text.split("\\s*(\\s|,|!|\\?|\\.|\\;)\\s*");

        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }

    }

}
