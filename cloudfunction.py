def main(event, context):
	file = event
	print(f"Processing file: {file['name']}.")

	print('Event ID: {}'.format(context.event_id))
	print('Event type: {}'.format(context.event_type))
	print('Bucket: {}'.format(event['bucket']))
	print('File: {}'.format(event['name']))
	print('Metageneration: {}'.format(event['metageneration']))
	print('Created: {}'.format(event['timeCreated']))
	print('Updated: {}'.format(event['updated']))

	from google.cloud import pubsub_v1

	# TODO(developer)
	project_id = "de-falabella"
	topic_id = "my-topic"

	publisher = pubsub_v1.PublisherClient()
	topic_path = publisher.topic_path(project_id, topic_id)

	#topic = publisher.create_topic(request={"name": topic_path})
	#print("Created topic: {}".format(topic.name))
	
	publisher.publish(topic_path, b'gs://{}/{}'.format(event['bucket'],event['name']), spam='eggs')