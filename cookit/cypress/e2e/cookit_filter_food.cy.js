describe('template spec', () => {

  /* ==== Test Created with Cypress Studio ==== */
  it('FilterFoodCebola', function() {
    /* ==== Generated with Cypress Studio ==== */
    cy.visit('http://localhost:8083/');
    cy.get('[href="/food/list"]').click();
    cy.get('.form-control').clear('c');
    cy.get('.form-control').type('cebol');
    cy.get('tbody > tr > :nth-child(1)').should('have.text', 'Cebola');
    /* ==== End Cypress Studio ==== */
  });
})