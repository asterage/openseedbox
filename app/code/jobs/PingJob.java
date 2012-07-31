package code.jobs;

import play.Logger;
import play.Play;
import play.jobs.Job;
import play.libs.WS;
import play.libs.WS.HttpResponse;

//No longer required with the move away from heroku
//@Every("30s")
public class PingJob extends Job {

	@Override
	public void doJob() throws Exception {
		//Ping the server to keep the Heroku dyno alive (only in prod mode though)
		if (Play.mode != Play.Mode.DEV) {
			Logger.info("Pinging client.openseedbox.com to keep the server alive...");
			HttpResponse res = WS.url("http://client.openseedbox.com/ping?ext=json").get();
			String response = res.getJson().getAsJsonObject().get("data").getAsString();
			Logger.info("Server responded: %s", response);
		}
	}
	
}