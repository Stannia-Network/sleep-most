package me.mrgeneralq.sleepmost.commands.dream;

import me.mrgeneralq.sleepmost.enums.DreamType;
import me.mrgeneralq.sleepmost.interfaces.IDreamService;
import me.mrgeneralq.sleepmost.interfaces.IMessageService;
import me.mrgeneralq.sleepmost.interfaces.ISubCommand;
import me.mrgeneralq.sleepmost.models.Dream;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DreamRemoveCommand implements ISubCommand {
    private final IDreamService dreamService;
    private final IMessageService messageService;

    public DreamRemoveCommand(IDreamService dreamService, IMessageService messageService) {
        this.dreamService = dreamService;
        this.messageService = messageService;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        //ARS <dreamName> [type]

        if(args.length == 0){
            //MSG specify a dream
            return true;
        }

        String dreamName = args[0];

        if(!this.dreamService.dreamExists(dreamName)){
            this.messageService.sendMessage(sender,
                    this.messageService.getMessagePrefixed("&cThis dream does not exist!").build());
            return true;
        }

        this.dreamService.removeDream(dreamName);

        //todo message
        this.messageService.sendMessage(sender, messageService.getMessagePrefixed("&aThe dream has been removed!").build());
        return true;
    }
}
