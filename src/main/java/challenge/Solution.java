package challenge;

import challenge.model.*;

public class Solution {
    public static void main(String[] args){
        FileSystemCacheSerializer serializer = new FileSystemCacheSerializer();

        String cacheStoreFileName = null;
        if(args.length >= 1){
            cacheStoreFileName = args[0];
        }

        try {
            final FileNode root = serializer.readFileSystem(cacheStoreFileName);
            final ExecutionContext context = new ExecutionContext(root);
            final CommandExecution commandResolver = new CommandExecution(context);

            commandResolver.loop();
            serializer.writeFileSystem(context.getRoot(), cacheStoreFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}