## Handle Images 

This worker takes a message from a rabbitmq queue and process an image on a AmazonS3 bucket based on the message info.

The message must fit the HandleImageDTO:
```
{
    "width": 
    "height": 
    "region":
    "bucketname"
    "fileName"
    "outputKey"
}
```
```mermaid
graph LR;
    A["Producer fa:fa-globe"]
    B[("Consumer (Worker) fa:fa-cubes")]
    C["AmazonS3 (Bucket) fa:fa-globe"]
    A-->B;
    B-->C;
    C-->B;
```
- Width and Height:  Describe the image size <br>
- Regions: The AmazonS3 Bucket region <br>
- BucketNames: The AmazonS3 Bucket name <br>
- fileNames: The name of the file on the bucket <br>
- outputKeys: The outputKey from the file <br>


## Tests



For now you can test the resize service by running the ImageResizeTest on the test directory