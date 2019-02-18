Player player = Bukkit.getPlayer(a[0]);
		if(player!=null) {
			NamespacedKey id= new NamespacedKey(m, "story/" + UUID.randomUUID().toString());
			
			
			//testear colores en title y description
			final String json= "{\r\n" + 
					"  \"criteria\": {\r\n" + 
					"    \"elytra\": {\r\n" + 
					"      \"trigger\": \"minecraft:impossible\",\r\n" + 
					"      \"conditions\": {\r\n" + 
					"        \"items\": []\r\n" + 
					"      }\r\n" + 
					"    }\r\n" + 
					"  },\r\n" + 
					"  \"display\": {\r\n" + 
					"    \"show_toast\": true,\r\n" + //este es la barra que aparece arriba
					"    \"background\": \"minecraft:textures/blocks/bedrock.png\",\r\n" + 
					"    \"icon\": {\r\n" + 
					"      \"item\": \"minecraft:nether_star\"\r\n" + 
					//"      \"data\": 0\r\n" + <<<< esto se quito en la 1.13
					"    },\r\n" + 
					"    \"description\": \"§3Monta a una gallina\",\r\n" +//esto lo pondra abajo de el logro en el hoover del chat 
					"    \"title\": \"§2Haz Flippado como Pajaro\",\r\n" + 
					"    \"announce_to_chat\": true,\r\n" + //lo mostrara al chat con la info de como esta ese logro
					"    \"frame\": \"task\"\r\n" + // challenge goal  task  "Challenge Complete" "Goal Reached" "Advancement Made"
					"  }\r\n" + 
					"}";
			if(Bukkit.getAdvancement(id) == null){
				Bukkit.getUnsafe().loadAdvancement(id, json);
				s.sendMessage("flipas");
				
			}
			if(!player.getAdvancementProgress(Bukkit.getAdvancement(id)).isDone())
				player.getAdvancementProgress(Bukkit.getAdvancement(id)).awardCriteria("elytra");
			
			
			Bukkit.getScheduler().runTaskLater(m, new Runnable() {
				@Override
				public void run() {
					if(player.getAdvancementProgress(Bukkit.getAdvancement(id)).isDone())
						player.getAdvancementProgress(Bukkit.getAdvancement(id)).revokeCriteria("elytra");//quita el logro de el jugador ya que no existe
					Bukkit.getScheduler().runTaskLater(m, new Runnable() {
						@Override
						public void run() {
							CraftMagicNumbers.INSTANCE.removeAdvancement(id);
						}
					}, 5);
					
					
				}
			}, 10);
			return true;
		}
		return false;
