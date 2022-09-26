package customcrafter.customcrafter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static customcrafter.customcrafter.SettingsLoad.materialAndResult;

public class Debug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        sender.sendMessage("materialAndResult:"+materialAndResult);
        return true;
    }
}
