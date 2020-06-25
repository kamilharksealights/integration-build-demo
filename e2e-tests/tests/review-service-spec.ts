import chai = require('chai');
import chaiHttp = require('chai-http');
chai.use(chaiHttp);

describe("review service", function () {

    this.timeout(0);

    it('should add product review', async () => {

        for (let i = 0; i < 10; i++) {
            const review = {productId: '1', rating: 2, comment: 'not very good'};
            const addReviewResponse = await chai.request("http://localhost:8079").post('/review').send(review);
            chai.expect(addReviewResponse).to.have.status(200);

            const getReviewResponse = await chai.request("http://localhost:8079").get('/review/1');
            chai.expect(getReviewResponse).to.have.status(200);
            chai.expect(getReviewResponse.body).deep.eq([review]);
            await sleep();
        }
    });

    function sleep() {
        return new Promise(resolve => {
            setTimeout(resolve, 2000);
        })
    }


})