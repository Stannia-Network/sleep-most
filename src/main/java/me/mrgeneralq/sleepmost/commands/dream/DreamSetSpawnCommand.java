package me.mrgeneralq.sleepmost.commands.dream;

import me.mrgeneralq.sleepmost.enums.MessageKey;
import me.mrgeneralq.sleepmost.interfaces.IDreamService;
import me.mrgeneralq.sleepmost.interfaces.IMessageService;
import me.mrgeneralq.sleepmost.interfaces.ISubCommand;
import me.mrgeneralq.sleepmost.models.Dream;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DreamSetSpawnCommand implements ISubCommand {

    private static final String DEFAULT_SPAWN_NAME = "default";

    private final IDreamService dreamService;
    private final IMessageService messageService;


    public DreamSetSpawnCommand(IDreamService dreamService, IMessageService messageService) {
        this.dreamService = dreamService;
        this.messageService = messageService;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        //ARS <dreamName> [type]
        if(!(sender instanceof Player)){
            this.messageService.sendMessage(sender, this.messageService.getMessagePrefixed(MessageKey.NO_CONSOLE_COMMAND).build());
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            //MSG specify a dream
            return true;
        }

        String dreamName = args[0];

        if(!this.dreamService.dreamExists(dreamName)){
            this.messageService.sendMessage(player,
                    this.messageService.getMessagePrefixed("&cThis dream does not exist!").build());
            return true;
        }

        String spawnName = DEFAULT_SPAWN_NAME;

        if(args.length > 1){
            spawnName = args[1];
        }

        Dream dream = dreamService.getDream(dreamName);
        dream.setSpawnLocation(spawnName ,player.getLocation());
        dreamService.updateDream(dream);

        //todo message
        this.messageService.sendMessage(player, messageService.getMessagePrefixed("&eThe spawn &f%spawn-name% &elocation has been set to the current location!")
                .setDream(dream)
                .setPlaceHolder("%spawn-name%",spawnName)
                .build());
        return true;
    }
}
