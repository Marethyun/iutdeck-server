# Messages

ok()
error(code, message)

## Service d'authentification / central

auth(username, password)
-> auth_tokens(user_token, game_token)

poke(server_address, server_port, server_name, rooms_available, rooms_max, offline)

push_history(application_token, player1_game_token, player2_game_token, winner_game_token, time_started, time_ended, events)

servers_list(user_token)
-> servers(list)

## Serveur