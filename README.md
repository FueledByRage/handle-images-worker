## Handle Images 

This worker takes a message from a rabbitmq queue and process an image on a AmazonS3 bucket based on the message info.

The message must fit the HandleImageDTO:
{
    "width": 
    "height": 
    "region":
    "bucketname"
    "fileName"
    "outputKey"
}

Width and Height => Describe the image size
Region => The AmazonS3 Bucket region
BucketName => The AmazonS3 Bucket name
fileName => The name of the file on the bucket
outputKey => The outputKey from the file


## Tests

<<<<<<< HEAD
For now you can test the resize service by running the ImageResizeTest on the test directory
=======
```mermaid
graph TD;
    A-->B;
    A-->C;
    B-->D;
    C-->D;
```
>>>>>>> 722a31e110ed3ece0bf700177c965171247e5282
